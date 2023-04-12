package tobyspring.helloboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Retention(RetentionPolicy.RUNTIME) // 언제까지 어노테이션이 살아있는지
@Target(value = ElementType.TYPE)   // 클래스, 인터페이스와 같은 TYPE에 적용
@Component
public @interface MyComponent {
}
