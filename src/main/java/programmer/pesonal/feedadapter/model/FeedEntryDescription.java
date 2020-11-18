package programmer.pesonal.feedadapter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;


@Data
//@Builder
@ToString(includeFieldNames = true)
@Entity
public class FeedEntryDescription {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private FeedEntry feedEntry;
    @Column(columnDefinition = "TEXT")
    private String value;
    private String type;
    private String mode;

    public FeedEntryDescription() {}

    @Builder
    public FeedEntryDescription(String value, String type, String mode){
        super();
        this.value = value;
        this.type = type;
        this.mode = mode;
    }

    public Long getId() {
        return id;
    }

    public FeedEntryDescription setId(Long id) {
        this.id = id;
        return this;
    }

    public FeedEntry getFeedEntry() {
        return feedEntry;
    }

    public FeedEntryDescription setFeedEntry(FeedEntry feedEntry) {
        this.feedEntry = feedEntry;
        return this;
    }

    public String getValue() {
        return value;
    }

    public FeedEntryDescription setValue(String value) {
        this.value = value;
        return this;
    }

    public String getType() {
        return type;
    }

    public FeedEntryDescription setType(String type) {
        this.type = type;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public FeedEntryDescription setMode(String mode) {
        this.mode = mode;
        return this;
    }
}
