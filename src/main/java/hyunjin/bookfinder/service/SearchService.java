package hyunjin.bookfinder.service;

import com.fasterxml.jackson.databind.JsonNode;
import hyunjin.bookfinder.model.BookBean;
import hyunjin.bookfinder.model.entity.SearchHistory;
import hyunjin.bookfinder.repository.SearchHistoryRepository;
import hyunjin.bookfinder.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriUtils;

import java.util.Date;
import java.util.List;

@Service
public class SearchService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Transactional
    public SearchHistory saveHistory(JsonNode book, BookBean bookSearch) {
        SearchHistory history = new SearchHistory();
        history.setUserId(bookSearch.getUserId());
        history.setSearchData(UriUtils.decode(bookSearch.getQuery(), "UTF-8"));
        history.setResultJson(book);
        history.setResult(JsonUtils.toJsonString(book));
        history.setCreatedDate(new Date());
        searchHistoryRepository.save(history);

        logger.info("new search history ID : {}", history.getSearchId());

        return history;
    }

    public List<SearchHistory> findRecentHistory(long userId) {
        return searchHistoryRepository.findByUserIdOrderByCreatedDateDesc(userId);
    }
}
