package programmer.pesonal.feedadapter.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import programmer.pesonal.feedadapter.model.FeedEntry;
import programmer.pesonal.feedadapter.model.FeedEntryDescription;
import programmer.pesonal.feedadapter.repository.FeedEntryDescriptionRepository;
import programmer.pesonal.feedadapter.repository.FeedEntryRepository;

public class FeedEntryResolver implements GraphQLResolver<FeedEntry> {
    private FeedEntryDescriptionRepository feedEntryDescriptionRepository;

    public FeedEntryResolver(FeedEntryDescriptionRepository feedEntryDescriptionRepository) {
        this.feedEntryDescriptionRepository = feedEntryDescriptionRepository;
    }

    public FeedEntryDescription getFeedEntryDescription(FeedEntry feedEntry) {
        return feedEntryDescriptionRepository.getOne(feedEntry.getFeedEntryDescription().getId());
    }

//    public Object getFeedEntry(@NotNull FeedEntry feedEntry) {
//        return feedEntryRepository.findById(feedEntry.getId());
//    }
////    public List<FeedEntry> getAllFeedEntry() {
////        return feedEntryRepository.findAll();
////    }
}
