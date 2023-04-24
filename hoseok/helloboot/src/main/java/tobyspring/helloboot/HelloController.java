package tobyspring.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private final HelloService helloService;

    public HelloController(final HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(final String name) {
        log.info("call HelloController#hello");
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        return helloService.sayHello(name);
    }

}
