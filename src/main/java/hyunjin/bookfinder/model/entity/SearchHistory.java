package hyunjin.bookfinder.model.entity;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "E_SEARCH_HISTORY")
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genID")
    @SequenceGenerator(name = "genID", sequenceName = "SEARCH_HISTORY_SEQ", allocationSize = 1)
    @Column(name = "SEARCH_ID", unique = true, nullable = false)
    private Long searchId;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "SEARCH_DATA")
    private String searchData;
    @Column(name = "RESULT")
    private String result;
    @Transient
    private JsonNode resultJson;
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    public Long getSearchId() {
        return searchId;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JsonNode getResultJson() {
        return resultJson;
    }

    public void setResultJson(JsonNode resultJson) {
        this.resultJson = resultJson;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
