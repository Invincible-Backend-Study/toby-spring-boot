package tobyspring.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    public String hello(final String name) {
        log.info("call HelloController#hello");
        return "hello " + name;
    }

}
