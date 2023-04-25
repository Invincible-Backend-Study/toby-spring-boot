package tobySpringBoot.config.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import tobySpringBoot.config.MyAutoConfiguration;
import tobySpringBoot.config.MyConfigurationProperties;

import java.util.Map;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // 우리가 만든 marker annotation만 이를 적용할 것이다. (MyConfigurationProperties)
                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class);
                if (annotation == null) return bean;

                Map<String, Object> attrs = AnnotationUtils.getAnnotationAttributes(annotation);  // 애노테이션에 있는 모든 value를 찾는다.
                String prefix = (String) attrs.get("prefix");  // 그 중 prefix 로 시작하는 녀석을 확인한다.

                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
