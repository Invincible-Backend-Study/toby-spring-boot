package tobyspring.helloboot.service;

public interface HelloService {
    String sayHello(String name);

    default String countOf(String name){
        return "0";
    };
}
