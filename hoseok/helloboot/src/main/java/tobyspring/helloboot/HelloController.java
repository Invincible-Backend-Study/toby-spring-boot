package tobyspring.helloboot;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private final HelloService helloService;

    public HelloController(final HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(final String name) {
        log.info("call HelloController#hello");
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
