package tobyspring.helloboot.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tobyspring.helloboot.annotation.MyComponent;
import tobyspring.helloboot.service.HelloService;

import java.util.Objects;


@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping(value = "/hello", produces = MediaType.TEXT_HTML_VALUE + ";charset=UTF-8")
    public String hello(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        return helloService.sayHello(name);
    }

}
