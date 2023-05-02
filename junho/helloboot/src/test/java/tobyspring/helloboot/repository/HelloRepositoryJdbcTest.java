package tobyspring.helloboot.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.HellobootTest;

@HellobootTest
class HelloRepositoryJdbcTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired HelloRepository helloRepository;


    @Test
    void findHelloFailed(){
        Assertions.assertThat(helloRepository.findeHello("leejunho")).isNull();
    }

    @Test
    void inceasCount(){
        helloRepository.increaseCount("leejunho");
        Assertions.assertThat(helloRepository.countOf9("leejunho")).isEqualTo(1);

        helloRepository.increaseCount("leejunho");
        Assertions.assertThat(helloRepository.countOf9("leejunho")).isEqualTo(2);
    }

}