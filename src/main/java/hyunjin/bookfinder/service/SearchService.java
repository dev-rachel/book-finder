package hyunjin.bookfinder.service;

import com.fasterxml.jackson.databind.JsonNode;
import hyunjin.bookfinder.model.BookBean;
import hyunjin.bookfinder.model.entity.SearchHistory;
import hyunjin.bookfinder.repository.SearchHistoryRepository;
import hyunjin.bookfinder.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class SearchService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private EntityManager entityManager;
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    public SearchService(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Transactional
    public SearchHistory saveHistory(JsonNode book, BookBean bookSearch) {
        SearchHistory history = new SearchHistory();
        history.setUserId(bookSearch.getUserId());
        history.setSearchData(bookSearch.getQuery());
        history.setResultJson(book);
        history.setResult(JsonUtil.toJsonString(book));
        history.setCreatedDate(new Date());
        entityManager.persist(history);

        logger.info("new search history ID : {}", history.getSearchId());

        return history;
    }

    public List<SearchHistory> findRecentHistory(long userId) {
        return searchHistoryRepository.findByUserIdOrderByCreatedDateDesc(userId);
    }
}
