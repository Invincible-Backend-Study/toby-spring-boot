package tobyspring.helloboot;

public class SimpleHelloService implements HelloService {

    @Override
    public String sayHello(final String name) {
        return "Hello " + name;
    }

}
