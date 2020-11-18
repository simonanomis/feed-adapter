package programmer.pesonal.feedadapter.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@ToString(includeFieldNames = true)
@Entity
public class FeedEntry {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy="feedEntry", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Setter(AccessLevel.NONE)
    private FeedEntryDescription feedEntryDescription;
    private String title;
    private String author;
    private LocalDateTime publicationDate;
    @Column(columnDefinition = "TEXT")
    private String url;
    private String imageUrl;

    public FeedEntry() {}

    @Builder
    public FeedEntry(FeedEntryDescription feedEntryDescription, String title, String author, LocalDateTime publicationDate, String url, String imageUrl) {
        super();
        this.feedEntryDescription = feedEntryDescription;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public FeedEntry setId(Long id) {
        this.id = id;
        return this;
    }

    public FeedEntryDescription getFeedEntryDescription() {
        return feedEntryDescription;
    }

    public String getTitle() {
        return title;
    }

    public FeedEntry setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public FeedEntry setAuthor(String author) {
        this.author = author;
        return this;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public FeedEntry setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public FeedEntry setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public FeedEntry setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Builder
    public void setFeedEntryDescription (FeedEntryDescription feedEntryDescription) {
        if (feedEntryDescription==null) {
            if (this.feedEntryDescription!=null) {
                this.feedEntryDescription.setFeedEntry(null);
            }
        }
        else {
            feedEntryDescription.setFeedEntry(this);
        }
        this.feedEntryDescription = feedEntryDescription;
    }
}
