package tobyspring.helloboot.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tobyspring.helloboot.repository.HelloRepository;

@Service
@Primary
public class SimpleHelloService implements HelloService {
    private final HelloRepository helloRepository;

    public SimpleHelloService(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @Override
    public String sayHello(String name){
        helloRepository.increaseCount(name);
        return "안녕" + name;
    }

    @Override
    public String countOf(String name) {
        return String.valueOf(helloRepository.countOf9(name));
    }

}
