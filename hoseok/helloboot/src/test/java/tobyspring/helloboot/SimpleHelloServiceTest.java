package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SimpleHelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }
}
