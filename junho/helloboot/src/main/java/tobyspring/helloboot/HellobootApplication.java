package tobyspring.helloboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobyspring.helloboot.annotation.MySpringApplication;

@MySpringApplication
public class HellobootApplication {
    private static final Logger logger = LoggerFactory.getLogger(HellobootApplication.class);

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
