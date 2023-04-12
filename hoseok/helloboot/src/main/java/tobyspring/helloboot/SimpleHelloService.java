package tobyspring.helloboot;

import org.springframework.stereotype.Service;

@Service
public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(final String name) {
        return "Hello " + name;
    }

}
