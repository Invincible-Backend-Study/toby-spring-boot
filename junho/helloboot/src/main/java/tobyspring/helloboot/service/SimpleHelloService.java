package tobyspring.helloboot.service;

import org.springframework.stereotype.Component;
import tobyspring.helloboot.annotation.MyComponent;

@MyComponent
public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name){
        return "안녕" + name;
    }

}
