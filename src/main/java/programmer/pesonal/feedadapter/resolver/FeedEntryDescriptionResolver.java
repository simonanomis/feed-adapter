package programmer.pesonal.feedadapter.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import programmer.pesonal.feedadapter.model.FeedEntry;
import programmer.pesonal.feedadapter.model.FeedEntryDescription;
import programmer.pesonal.feedadapter.repository.FeedEntryDescriptionRepository;
import programmer.pesonal.feedadapter.repository.FeedEntryRepository;

public class FeedEntryDescriptionResolver implements GraphQLResolver<FeedEntryDescription> {
    private FeedEntryRepository feedEntryRepository;

    public FeedEntryDescriptionResolver(FeedEntryRepository feedEntryRepository) {
        this.feedEntryRepository = feedEntryRepository;
    }

    public FeedEntry getFeedEntry(FeedEntryDescription feedEntryDescription) {
        return feedEntryRepository.getOne(feedEntryDescription.getFeedEntry().getId());
    }
}
