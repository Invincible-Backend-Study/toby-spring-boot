package tobyspring.helloboot;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private final HelloService helloService;

    public HelloController(final HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(final String name) {
        log.info("call HelloController#hello");
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
