package hyunjin.bookfinder.controller;

import hyunjin.bookfinder.model.BookmarkBean;
import hyunjin.bookfinder.model.SearchBaseBean;
import hyunjin.bookfinder.model.entity.Bookmark;
import hyunjin.bookfinder.service.BookmarkService;
import hyunjin.bookfinder.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.BeanParam;
import java.util.List;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping("/user/{user_id}/book/{book_id}")
    public String create(@RequestBody BookmarkBean bookmark, @PathVariable("user_id") long userId, @PathVariable("book_id") long bookId) {

        logger.info("request data : {}, user_id : {}, book_id : {}", bookmark, userId, bookId);

        Bookmark result = bookmarkService.create(bookmark, userId, bookId);

        logger.info("result : {}", result);

        return JsonUtils.toJsonString(result);
    }

    @GetMapping
    public String read(@BeanParam SearchBaseBean search) {

        logger.info("request data : {}", search);

        List<Bookmark> result = bookmarkService.findAll(search);

        logger.info("result : {}", result);

        return JsonUtils.toJsonString(result);
    }

    @DeleteMapping("/{bookmark_id}")
    public String delete(@PathVariable("bookmark_id") long bookmarkId) {

        try {
            logger.info("request data : {}", bookmarkId);

            bookmarkService.delete(bookmarkId);

            logger.info("{}bookmark delete", bookmarkId);

            return bookmarkId + "bookmark delete";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
