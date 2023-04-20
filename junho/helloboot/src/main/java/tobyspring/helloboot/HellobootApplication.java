package tobyspring.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import tobyspring.helloboot.annotation.MySpringApplication;

@MySpringApplication
public class HellobootApplication {
    private static final Logger logger = LoggerFactory.getLogger(HellobootApplication.class);

    public static void main(String[] args) {
        tobyspring.config.MySpringApplication.run(HellobootApplication.class,args);
    }


}
