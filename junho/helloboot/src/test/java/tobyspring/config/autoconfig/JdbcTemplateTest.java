package tobyspring.config.autoconfig;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.HellobootTest;

@HellobootTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    void insertAndQuery(){
        jdbcTemplate.update("insert into hello values(?,?)","leejunho",3);
        jdbcTemplate.update("insert into hello values(?,?)","leejunho2",1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        Assertions.assertThat(count).isEqualTo(2);
    }


}
