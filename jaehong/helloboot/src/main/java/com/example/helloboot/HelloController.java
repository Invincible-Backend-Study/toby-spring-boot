package com.example.helloboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @RequestMapping("/hello")
    public String hello(String name) {
        return helloService.sayHello(name);
    }
}
