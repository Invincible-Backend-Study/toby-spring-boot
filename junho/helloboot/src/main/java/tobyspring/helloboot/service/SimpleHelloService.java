package tobyspring.helloboot.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name){
        System.out.println(name);
        return "안녕" + name;
    }

}
