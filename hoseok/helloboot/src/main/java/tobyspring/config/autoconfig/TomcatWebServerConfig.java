package tobyspring.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat") // 여길 통과하면
public class TomcatWebServerConfig {

    @Value("${contextPath}") // BeanFactoryPostProcessor 라는 스프링 컨테이너의 후처리기를 빈으로 등록하면 정상적으로 치환할 수 있다.
    String contextPath;

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean  // 요런 타입의 빈이 있다면(유저 구성 정보) 등록하지 않고, 없다면 등록한다.
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        System.out.println(contextPath);
        factory.setContextPath(this.contextPath);
        return factory;
    }

}
