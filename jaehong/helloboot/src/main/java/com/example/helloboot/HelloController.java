package com.example.helloboot;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String name){
        return String.format("hello %s", name);
    }
}
