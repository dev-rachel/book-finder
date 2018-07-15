package hyunjin.bookfinder.model.entity;

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
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genID")
    @SequenceGenerator(name = "genID", sequenceName = "E_BOOKMARK_SEQ", allocationSize = 1)
    @Column(name = "BOOKMARK_ID", unique = true, nullable = false)
    private Long bookmarkId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "BOOK_ID")
    private Long bookId;
    @Column(name = "URI")
    private String uri;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "DELETED_DATE")
    private Date deletedDate;


    public Long getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Long bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

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