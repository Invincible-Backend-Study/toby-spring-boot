package tobyspring.helloboot.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService service = new SimpleHelloService();
        String ret = service.sayHello("하이");

        Assertions.assertThat(ret).isEqualTo("안녕하이");
    }

}