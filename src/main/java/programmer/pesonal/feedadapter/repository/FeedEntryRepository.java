package programmer.pesonal.feedadapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import programmer.pesonal.feedadapter.model.FeedEntry;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FeedEntryRepository extends JpaRepository<FeedEntry, Long> {
    @Query(value="select f.* from FEED_ENTRY f where f.URL = ?1", nativeQuery = true)
    FeedEntry existsByUrl(@Param("url") String url);
}
