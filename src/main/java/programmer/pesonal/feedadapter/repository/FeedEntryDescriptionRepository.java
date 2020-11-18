package programmer.pesonal.feedadapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import programmer.pesonal.feedadapter.model.FeedEntryDescription;

public interface FeedEntryDescriptionRepository extends JpaRepository<FeedEntryDescription, Long> {
}
