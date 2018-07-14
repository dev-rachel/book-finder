package hyunjin.bookfinder.repository;

import hyunjin.bookfinder.model.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
