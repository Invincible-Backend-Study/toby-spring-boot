package tobyspring.helloboot.service;

public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(String name){
        return "안녕" + name;
    }

}
