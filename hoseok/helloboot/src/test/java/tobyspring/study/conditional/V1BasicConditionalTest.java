package tobyspring.study.conditional;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 단순 @Conditional 사용하여 true false 반환하여 빈 등록 확인 테스트
 */
public class V1BasicConditionalTest {

    @Test
    void conditional() {
        new ApplicationContextRunner().withUserConfiguration(Config1.class)
                .run(context -> {
                    Assertions.assertThat(context).hasSingleBean(MyBean.class);
                    Assertions.assertThat(context).hasSingleBean(Config1.class);
                });   // Config1 설정을 통해 만들어진 컨텍스트

        // Conditional 애노테이션에 지정된 클래스가 false를 반환한다면 빈 등록 안됨
        new ApplicationContextRunner().withUserConfiguration(Config2.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config2.class);
                });
    }

    @Configuration
    @Conditional(TrueCondition.class)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @Conditional(FalseCondition.class)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {}

    static class TrueCondition implements Condition {
        @Override
        public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

}
