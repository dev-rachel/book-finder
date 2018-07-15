package hyunjin.bookfinder.service;

import hyunjin.bookfinder.model.UserBean;
import hyunjin.bookfinder.model.entity.User;
import hyunjin.bookfinder.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User create(UserBean userInfo) {
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setPassword(userInfo.getPassword());
        user.setCreatedDate(new Date());
        userRepository.save(user);

        logger.info("new user ID : {}", user.getUserId());

        return user;
    }

    public User login(String username, String password) {
        return Optional.ofNullable(userRepository.findByUsernameAndPassword(username, password))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username : " + username + ", password : " + password));
    }
}