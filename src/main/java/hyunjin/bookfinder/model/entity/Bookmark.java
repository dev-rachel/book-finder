package hyunjin.bookfinder.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "E_BOOKMARK")
@JsonPropertyOrder({
        "bookmark_id", "user_id", "book_id", "uri", "created_date", "deleted_date"
})
public class Bookmark {
    @JsonProperty("bookmark_id")
    private Long bookmarkId;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("book_id")
    private Long bookId;
    private String uri;
    @JsonProperty("created_date")
    private Date createdDate;
    @JsonProperty("deleted_date")
    private Date deletedDate;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genID")
    @SequenceGenerator(name = "genID", sequenceName = "E_BOOKMARK_SEQ", allocationSize = 1)
    @Column(name = "BOOKMARK_ID", unique = true, nullable = false)
    public Long getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "BOOK_ID")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Column(name = "URI")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Column(name = "CREATED_DATE")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "DELETED_DATE")
    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}