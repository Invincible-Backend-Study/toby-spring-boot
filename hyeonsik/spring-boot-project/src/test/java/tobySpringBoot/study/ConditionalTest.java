package tobySpringBoot.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.beans.BeanProperty;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConditionalTest {
    @Test
    void conditional() {
//        // true
//        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
//        contextRunner.withUserConfiguration(Config1.class)
//                        .run(context -> {
//                            assertThat(context).hasSingleBean(MyBean.class);
//                            assertThat(context).hasSingleBean(Config1.class);
//                        });
//
//        // false
//        new ApplicationContextRunner().withUserConfiguration(Config1.class)
//                .run(context -> {
//                    assertThat(context).doesNotHaveBean(MyBean.class);
//                    assertThat(context).doesNotHaveBean(Config1.class);
//                });

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1 {  // <- static으로 선언하면 inner class 처럼 취급되지 않는다. 대신 상위 클래스가 마치 package 역할을 한다.
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (Boolean) annotationAttributes.get("value");
        }
    }

}
