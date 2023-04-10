package com.example.helloboot;

public class SimpleHelloService implements HelloService{
    @Override
    public String sayHello(String name) {
        return "hello "+ name;
    }
}
