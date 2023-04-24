package tobyspring.config.autoconfig;

import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.MyConfigurationProperties;

@MyAutoConfiguration
public class PropertyPostProcessConfig {

    @Bean
    BeanPostProcessor propertyPostProcessor(Environment env) {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(final Object bean, final String beanName)
                    throws BeansException {
                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(),
                        MyConfigurationProperties.class);
                if (annotation == null) {
                    return bean;
                }

                Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(annotation);
                String prefix = (String) attributes.get("prefix");

                // name값으로 prefix를 넘겨주면 프로퍼티 바인딩을 할때 앞에 prefix를 붙여서 뒤에 나오는 이름이 클래스의 프로퍼티 이름과 일치하는지 체크한다.
                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
