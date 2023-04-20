package com.example.helloboot;


import java.util.LinkedList;
import java.util.Queue;
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
        Queue<Integer> queue = new LinkedList<>();
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
        return helloService.sayHello(name);
    }
}
