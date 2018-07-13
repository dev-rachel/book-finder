package hyunjin.bookfinder.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SearchBaseBean {
    private Integer page;
    private Integer size;
    private String sort;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }
}
