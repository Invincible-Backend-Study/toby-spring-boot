package tobyspring.helloboot.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.HellobootTest;
import tobyspring.helloboot.repository.HelloRepositoryJdbc;

@HellobootTest
class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService service = new SimpleHelloService(new HelloRepositoryJdbc(new JdbcTemplate()));
        String ret = service.sayHello("하이");

        Assertions.assertThat(ret).isEqualTo("안녕하이");
    }

}