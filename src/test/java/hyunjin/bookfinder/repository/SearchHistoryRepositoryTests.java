package hyunjin.bookfinder.repository;

import hyunjin.bookfinder.model.entity.SearchHistory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchHistoryRepositoryTests {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    @After
    public void cleanup() {
        searchHistoryRepository.deleteAll();
    }

    @Test
    public void createHistory() {
        SearchHistory history = new SearchHistory();
        history.setUserId(500L);
        history.setSearchData("hello");
        history.setResultJson(null);
        history.setResult(null);
        history.setCreatedDate(new Date());
        searchHistoryRepository.save(history);

        SearchHistory history2 = new SearchHistory();
        history2.setUserId(500L);
        history2.setSearchData("jungcha");
        history2.setResultJson(null);
        history2.setResult(null);
        history2.setCreatedDate(new Date());
        searchHistoryRepository.save(history2);

        List<SearchHistory> result = searchHistoryRepository.findByUserIdOrderByCreatedDateDesc(500L);

        checkResultList(result);
    }

    private void checkResultList(List<SearchHistory> result) {
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(2));
        assertThat(result.get(0), is(notNullValue()));
        assertThat(result.get(0).getSearchId(), is(notNullValue()));
    }
}
