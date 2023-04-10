package tobyspring.helloboot;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    public String hello(final String name) {
        log.info("call HelloController#hello");
        SimpleHelloService simpleHelloService = new SimpleHelloService();
        return simpleHelloService.sayHello(Objects.requireNonNull(name));
    }

}
