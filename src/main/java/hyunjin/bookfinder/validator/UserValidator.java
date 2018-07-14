package hyunjin.bookfinder.validator;

import hyunjin.bookfinder.model.UserBean;
import hyunjin.bookfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserBean.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserBean user = (UserBean) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");

        if (Objects.nonNull(userService.findByUsername(user.getUsername()))) {
            errors.rejectValue("username", "Duplicate username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

    }
}
