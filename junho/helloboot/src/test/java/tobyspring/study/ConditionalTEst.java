package tobyspring.study;

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

public class ConditionalTEst {
    @Test
    void conditional() {
        ApplicationContextRunner acr  = new ApplicationContextRunner();

        acr.withUserConfiguration(Config1.class).run(context -> {
            Assertions.assertThat(context).hasSingleBean(MyBean.class);
        });

        acr.withUserConfiguration(Config2.class).run(context -> {
            Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
        });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional{};

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional{};


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    };

    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean mybean() {
            return new MyBean();
        }
    }

    static class MyBean {
    }

    private static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    private static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }


    private static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> element = metadata.getAnnotationAttributes(
                    BooleanConditional.class.getName());
            return (boolean) element.get("value");
        }
    }
}
