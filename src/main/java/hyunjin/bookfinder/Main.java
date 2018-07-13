package hyunjin.bookfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan({
        "hyunjin.bookfinder.controller",
        "hyunjin.bookfinder.service",
        "hyunjin.bookfinder.model"})
@SpringBootApplication
public class Main extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }
}
