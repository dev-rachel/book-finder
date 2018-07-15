package hyunjin.bookfinder.controller;

import hyunjin.bookfinder.model.UserBean;
import hyunjin.bookfinder.model.entity.SearchHistory;
import hyunjin.bookfinder.service.SearchService;
import hyunjin.bookfinder.service.UserService;
import hyunjin.bookfinder.util.JsonUtils;
import hyunjin.bookfinder.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.BeanParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registration(@RequestBody UserBean user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.create(user);

        return "sign up";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@BeanParam UserBean user) {

        userService.login(user.getUsername(), user.getPassword());

        return "log in";
    }

    @GetMapping("/{user_id}/book/search")
    public String findRecentHistory(@PathVariable("user_id") long userId) {

        List<SearchHistory> result = searchService.findRecentHistory(userId);
        return JsonUtils.toJsonString(result);
    }
}
