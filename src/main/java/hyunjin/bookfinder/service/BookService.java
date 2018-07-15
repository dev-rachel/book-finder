package hyunjin.bookfinder.service;

import com.fasterxml.jackson.databind.JsonNode;
import hyunjin.bookfinder.model.BookBean;
import hyunjin.bookfinder.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.util.Objects;

@Service
public class BookService extends ApiRequestService {
    @Value("${kakao.book.search}")
    protected String apiUrl;
    @Value("${kakao.app.key}")
    protected String appKey;

    public JsonNode search(HttpHeaders headers, BookBean bookSearch) throws Exception {
        headers.add("Authorization", appKey);

        String uriStr = UriComponentsBuilder
                .fromUriString(apiUrl)
                .queryParam("query", UriUtils.decode(bookSearch.getQuery(), "UTF-8"))
                .queryParam("sort", bookSearch.getSort())
                .queryParam("page", bookSearch.getPage())
                .queryParam("size", bookSearch.getSize())
                .queryParam("target", bookSearch.getTarget())
                .queryParam("category", bookSearch.getCategory())
                .build().encode().toString();

        ResponseEntity<Object> responseEntity = requestApi(uriStr, headers, HttpMethod.GET, "", null);

        //TODO : 검색결과  integer로 보이는 얘들 string으로 변경(category)
        return JsonUtils.fromJson(Objects.toString(responseEntity.getBody(), null));
    }
}
