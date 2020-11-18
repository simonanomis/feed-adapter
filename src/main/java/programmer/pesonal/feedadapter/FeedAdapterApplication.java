package programmer.pesonal.feedadapter;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import programmer.pesonal.feedadapter.exception.GraphQLErrorAdapter;
import programmer.pesonal.feedadapter.model.FeedEntry;
import programmer.pesonal.feedadapter.model.FeedEntryDescription;
import programmer.pesonal.feedadapter.repository.FeedEntryDescriptionRepository;
import programmer.pesonal.feedadapter.repository.FeedEntryRepository;
import programmer.pesonal.feedadapter.resolver.FeedEntryDescriptionResolver;
import programmer.pesonal.feedadapter.resolver.FeedEntryResolver;
import programmer.pesonal.feedadapter.resolver.Mutation;
import programmer.pesonal.feedadapter.resolver.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
@PropertySource("classpath:application.properties")
//public class FeedAdapterApplication implements CommandLineRunner {
public class FeedAdapterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeedAdapterApplication.class, args);
    }

    @Bean
    public FeedEntryResolver feedEntryResolver(FeedEntryDescriptionRepository feedEntryDescriptionRepository) {
        return new FeedEntryResolver(feedEntryDescriptionRepository);
    }

    @Bean
    public FeedEntryDescriptionResolver feedEntryDescriptionResolver(FeedEntryRepository feedEntryRepository) {
        return new FeedEntryDescriptionResolver(feedEntryRepository);
    }

    @Bean
    public Query query(FeedEntryRepository feedEntryRepository, FeedEntryDescriptionRepository feedEntryDescriptionRepository) {
        return new Query(feedEntryRepository, feedEntryDescriptionRepository);
    }

    @Bean
    public Mutation mutation(FeedEntryRepository feedEntryRepository, FeedEntryDescriptionRepository feedEntryDescriptionRepository) {
        return new Mutation(feedEntryRepository, feedEntryDescriptionRepository);
    }

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new GraphQLErrorHandler() {
            @Override
            public List<GraphQLError> processErrors(List<GraphQLError> errors) {
                List<GraphQLError> clientErrors = errors.stream()
                        .filter(this::isClientError)
                        .collect(Collectors.toList());

                List<GraphQLError> serverErrors = errors.stream()
                        .filter(e -> !isClientError(e))
                        .map(GraphQLErrorAdapter::new)
                        .collect(Collectors.toList());

                List<GraphQLError> e = new ArrayList<>();
                e.addAll(clientErrors);
                e.addAll(serverErrors);
                return e;
            }

            protected boolean isClientError(GraphQLError error) {
                return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
            }
        };
    }
}
