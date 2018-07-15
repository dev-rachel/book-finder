package hyunjin.bookfinder.repository;

import hyunjin.bookfinder.model.entity.Bookmark;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookmarkRepositoryTests {

    @Autowired
    BookmarkRepository bookmarkRepository;

    @After
    public void cleanup() {
        bookmarkRepository.deleteAll();
    }

    @Test
    public void createBookmark() {
        Bookmark bookmark = new Bookmark();
        bookmark.setUserId(1111L);
        bookmark.setBookId(2222L);
        bookmark.setUri("test.co.kr/01");
        bookmark.setCreatedDate(new Date());
        bookmarkRepository.save(bookmark);

        List<Bookmark> bookmarkList = bookmarkRepository.findAll();

        Assert.assertNotNull(bookmarkList);
    }

}
