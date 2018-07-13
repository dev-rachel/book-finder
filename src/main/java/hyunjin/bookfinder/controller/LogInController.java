package hyunjin.bookfinder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log-in")
public class LogInController {

    private Logger logger = LoggerFactory.getLogger(getClass());
}
