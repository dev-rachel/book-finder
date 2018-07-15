package hyunjin.bookfinder.repository;

import hyunjin.bookfinder.model.entity.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void createHistory() {
        User user = new User();
        user.setUsername("rachel");
        user.setPassword("1234");
        user.setCreatedDate(new Date());
        userRepository.save(user);

        User result = userRepository.findByUsername("rachel");

        checkResult(result);

        assertNull(userRepository.findByUsernameAndPassword("rachel","12345"));
        assertNotNull(userRepository.findByUsernameAndPassword("rachel","1234"));
    }

    private void checkResult(User result) {
        assertThat(result, is(notNullValue()));
        assertThat(result.getUserId(), is(notNullValue()));
    }

}
