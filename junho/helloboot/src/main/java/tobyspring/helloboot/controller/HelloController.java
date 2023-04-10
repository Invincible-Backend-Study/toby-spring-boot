package tobyspring.helloboot.controller;

import tobyspring.helloboot.service.HelloService;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
