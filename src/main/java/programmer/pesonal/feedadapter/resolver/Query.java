package programmer.pesonal.feedadapter.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import programmer.pesonal.feedadapter.model.FeedEntry;
import programmer.pesonal.feedadapter.model.FeedEntryDescription;
import programmer.pesonal.feedadapter.repository.FeedEntryDescriptionRepository;
import programmer.pesonal.feedadapter.repository.FeedEntryRepository;

public class Query implements GraphQLQueryResolver {
    private FeedEntryRepository feedEntryRepository;
    private FeedEntryDescriptionRepository feedEntryDescriptionRepository;

    public Query(FeedEntryRepository feedEntryRepository, FeedEntryDescriptionRepository feedEntryDescriptionRepository) {
        this.feedEntryRepository = feedEntryRepository;
        this.feedEntryDescriptionRepository = feedEntryDescriptionRepository;
    }
    public Iterable<FeedEntry> findAllFeedEntries() {
        return feedEntryRepository.findAll();
    }
    public Iterable<FeedEntryDescription> findAllFeedEntryDescriptions() {
        return feedEntryDescriptionRepository.findAll();
    }
    public long countFeedEntries() {
        return feedEntryRepository.count();
    }
    public long countFeedEntryDescriptions() {
        return feedEntryDescriptionRepository.count();
    }
}
