package hyunjin.bookfinder.controller;

import hyunjin.bookfinder.model.UserBean;
import hyunjin.bookfinder.model.entity.SearchHistory;
import hyunjin.bookfinder.model.entity.User;
import hyunjin.bookfinder.service.SearchService;
import hyunjin.bookfinder.service.UserService;
import hyunjin.bookfinder.util.JsonUtils;
import hyunjin.bookfinder.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.BeanParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SearchService searchService;

    @PostMapping(value = "/signup")
    public String registration(@RequestBody UserBean user, BindingResult bindingResult) {

        logger.info("request data : {}", user);

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User signup = userService.create(user);

        logger.info("result : {}", signup);

        return "sign up";
    }

    @GetMapping(value = "/login")
    public String login(@BeanParam UserBean user) {

        logger.info("request data : {}", user);

        User login = userService.login(user.getUsername(), user.getPassword());

        logger.info("result : {}", login);

        return "log in";
    }

    @GetMapping("/{user_id}/book/search")
    public String findRecentHistory(@PathVariable("user_id") long userId) {

        logger.info("request data : {}", userId);

        List<SearchHistory> result = searchService.findRecentHistory(userId);

        logger.info("result : {}", result);

        return JsonUtils.toJsonString(result);
    }
}
