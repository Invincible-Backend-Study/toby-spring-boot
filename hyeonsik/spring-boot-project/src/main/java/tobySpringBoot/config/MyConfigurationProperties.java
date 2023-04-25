package tobySpringBoot.config;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)  // marking하는 역할이므로
@Component
public @interface MyConfigurationProperties {
    String prefix();
}
