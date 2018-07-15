package hyunjin.bookfinder.repository;

import hyunjin.bookfinder.model.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    List<SearchHistory> findByUserIdOrderByCreatedDateDesc(long userId);
}
