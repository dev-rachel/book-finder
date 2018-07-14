package hyunjin.bookfinder.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BookmarkBean {
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }
}
