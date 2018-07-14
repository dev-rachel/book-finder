package hyunjin.bookfinder.controller;

import com.fasterxml.jackson.databind.JsonNode;
import hyunjin.bookfinder.model.BookBean;
import hyunjin.bookfinder.service.BookService;
import hyunjin.bookfinder.service.SearchService;
import hyunjin.bookfinder.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.BeanParam;
import java.util.Objects;

@Controller
@RequestMapping("/book")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookService bookService;

    @Autowired
    private SearchService searchService;

    @GetMapping
    public String search(@RequestHeader HttpHeaders headers, @BeanParam BookBean bookSearch) throws Exception {

        JsonNode book = bookService.search(headers, bookSearch);

        if (Objects.nonNull(book)) {
            searchService.saveHistory(book, bookSearch);
        }

        return JsonUtils.toJsonString(book);
    }
}
