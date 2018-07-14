package hyunjin.bookfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({
        "hyunjin.bookfinder.controller",
        "hyunjin.bookfinder.service",
        "hyunjin.bookfinder.model"})
@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        }
)
@EnableJpaRepositories({
        "hyunjin.bookfinder.repository"
})
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}
