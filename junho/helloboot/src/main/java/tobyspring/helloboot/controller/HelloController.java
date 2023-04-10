package tobyspring.helloboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tobyspring.helloboot.service.SimpleHelloService;

import java.util.Objects;

public class HelloController {
    public String hello(String name) {
        SimpleHelloService service = new SimpleHelloService();
        return service.sayHello(Objects.requireNonNull(name));
    }
}
