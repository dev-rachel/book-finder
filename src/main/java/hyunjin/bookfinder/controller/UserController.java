package hyunjin.bookfinder.controller;

import hyunjin.bookfinder.model.UserBean;
import hyunjin.bookfinder.service.UserService;
import hyunjin.bookfinder.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.BeanParam;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

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
}
