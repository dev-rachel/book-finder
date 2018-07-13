package hyunjin.bookfinder.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BookBean extends SearchBaseBean {
    private String query;
    private String target;
    private Integer category;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }
}
