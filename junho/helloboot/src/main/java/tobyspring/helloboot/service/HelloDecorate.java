package tobyspring.helloboot.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class HelloDecorate implements HelloService{

    private final HelloService helloService;

    public HelloDecorate(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return  "*" + helloService.sayHello(name) + "*";
    }

    @Override
    public String countOf(String name) {
        return helloService.countOf(name);
    }
}
