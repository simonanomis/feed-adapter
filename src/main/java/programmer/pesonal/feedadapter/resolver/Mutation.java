package programmer.pesonal.feedadapter.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import programmer.pesonal.feedadapter.model.FeedEntry;
import programmer.pesonal.feedadapter.model.FeedEntryDescription;
import programmer.pesonal.feedadapter.repository.FeedEntryDescriptionRepository;
import programmer.pesonal.feedadapter.repository.FeedEntryRepository;

public class Mutation implements GraphQLMutationResolver {
    private FeedEntryRepository feedEntryRepository;
    private FeedEntryDescriptionRepository feedEntryDescriptionRepository;

    public Mutation(FeedEntryRepository feedEntryRepository, FeedEntryDescriptionRepository feedEntryDescriptionRepository) {
        this.feedEntryRepository = feedEntryRepository;
        this.feedEntryDescriptionRepository = feedEntryDescriptionRepository;
    }

    public FeedEntry newFeedEntry(String title, String author) {
        FeedEntry feedEntry = new FeedEntry();
        feedEntry.setTitle(title);
        feedEntry.setAuthor(author);
        feedEntryRepository.save(feedEntry);
        return feedEntry;
    }

    public FeedEntryDescription newFeedEntryDescription(String type, String mode, String value) {
        FeedEntryDescription feedEntryDescription = new FeedEntryDescription();
        feedEntryDescription.setType(type);
        feedEntryDescription.setMode(mode);
        feedEntryDescription.setValue(value);

        feedEntryDescriptionRepository.save(feedEntryDescription);
        return feedEntryDescription;
    }

    public boolean deleteFeedEntry(Long id) {
        feedEntryRepository.deleteById(id);
        return true;
    }

    public boolean deleteFeedEntryDescription(Long id) {
        feedEntryDescriptionRepository.deleteById(id);
        return true;
    }

}
