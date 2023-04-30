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
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor processor(Environment env){
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                MyConfigurationProperties annotaion = AnnotationUtils.findAnnotation(bean.getClass(),
                        MyConfigurationProperties.class);
                if(annotaion == null){
                    return bean;
                }

                Map<String, Object> attrs = AnnotationUtils.getAnnotationAttributes(annotaion);
                String prepix = (String) attrs.get("prefix");

                return Binder.get(env).bindOrCreate(prepix, bean.getClass());
            }
        };
    }
}
