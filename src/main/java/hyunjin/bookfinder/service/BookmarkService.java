package hyunjin.bookfinder.service;

import hyunjin.bookfinder.model.BookmarkBean;
import hyunjin.bookfinder.model.SearchBaseBean;
import hyunjin.bookfinder.model.entity.Bookmark;
import hyunjin.bookfinder.repository.BookmarkRepository;
import hyunjin.bookfinder.util.TypeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.NoContentException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Transactional
    public Bookmark create(BookmarkBean bookmarkInfo, long userId, long bookId) {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(userId);
        bookmark.setBookId(bookId);
        bookmark.setUri(bookmarkInfo.getUri());
        bookmark.setCreatedDate(new Date());
        bookmarkRepository.save(bookmark);

        logger.info("new bookmark ID : {}", bookmark.getBookmarkId());

        return bookmark;
    }

    @Transactional
    public void delete(long bookmarkId) throws NoContentException {
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow(
                () -> new NoContentException("not found bookmark - id:" + bookmarkId)
        );
        Optional.of(bookmark).ifPresent(value -> {
            value.setDeletedDate(new Date());
            bookmarkRepository.save(value);
        });
    }

    public List<Bookmark> findAll(SearchBaseBean search) {
        Pageable pageRequest = PageRequest.of(TypeUtils.getDefault(search.getPage(), 1) - 1,
                TypeUtils.getDefault(search.getSize(), 10),
                new Sort(Sort.Direction.DESC, StringUtils.defaultString(search.getSort(), "createdDate")));
        return bookmarkRepository.findAll(pageRequest).getContent();
    }
}
