package tobySpringBoot.learn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping()
@Controller
public class HelloController {
    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
