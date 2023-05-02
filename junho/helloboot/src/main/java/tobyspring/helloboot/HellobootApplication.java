package tobyspring.helloboot;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.helloboot.annotation.MySpringApplication;

@MySpringApplication
public class HellobootApplication {
    private static final Logger logger = LoggerFactory.getLogger(HellobootApplication.class);

    private final JdbcTemplate jdbcTemplate;

    public HellobootApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init(){
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key,count int)");
    }

    @Bean
    ApplicationRunner applicationRunner(Environment env){
        return args -> {
            String str = env.getProperty("my.name");
            System.out.println("my.name" + str);
        };
    }
    public static void main(String[] args) {
        tobyspring.config.MySpringApplication.run(HellobootApplication.class,args);
    }


}
