package tobyspring.study;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.zaxxer.hikari.pool.HikariProxyCallableStatement;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.HellobootTest;
import tobyspring.helloboot.repository.HelloRepository;
import tobyspring.helloboot.service.HelloService;

@HellobootTest
public class HelloServiceCountTest {
    @Autowired
    HelloService helloService;

    @Autowired
    HelloRepository helloRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void sayHelloIncreaseCount(){
        IntStream.rangeClosed(1,10).forEach(count->{
            helloService.sayHello("leejunho");
            assertThat(helloRepository.countOf9("leejunho")).isEqualTo(count);
        });
    }
}
