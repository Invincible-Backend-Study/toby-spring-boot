package tobyspring.study.conditional;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class V3ConditionalTest {

    @Test
    void conditional() {
        // Conditional 애노테이션에 지정된 클래스가 true를 반환한다면 빈 등록됨
        new ApplicationContextRunner().withUserConfiguration(Config1.class)
                .run(context -> {
                    Assertions.assertThat(context).hasSingleBean(MyBean.class);
                    Assertions.assertThat(context).hasSingleBean(Config1.class);
                });

        // Conditional 애노테이션에 지정된 클래스가 false를 반환한다면 빈 등록 안됨
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config2.class);
                });


    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();    // 싱글 앨리먼트만 사용하면 엘리먼트를 넘길때 이름을 생략할 수 있다.
    }


    // 선택하고픈 Condition을 애노테이션의 element로 전달해 넘겨 선택할 수 있다.
    @Configuration
    @BooleanConditional(true)
    static class Config1 {

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
        public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
            // 애노테이션에 붙은 엘리먼트의 값을 활용해 컨디션의 조건을 결정하는 로직을 만들 수 있다.
            // 이렇게 되면 컨디션을 가지고 있는 애노테이션이 사용되는 환경에서 엘리먼트의 값을 무엇을 사용하는지에 따라 다양한 정보를 가져와 참고할 수 있음
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(
                    BooleanConditional.class.getName());
            System.out.println(annotationAttributes);
            Boolean value = (Boolean) annotationAttributes.get("value");
            return value;
        }
    }
}
