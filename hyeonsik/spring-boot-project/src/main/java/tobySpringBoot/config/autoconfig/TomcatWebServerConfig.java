package tobySpringBoot.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import tobySpringBoot.config.ConditionalMyOnClass;
import tobySpringBoot.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {


    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean  // 이미 동일한 타입의 빈이 등록되어있는가? 그렇지 않다면 빈으로 등록해줘라! 라는 condition
    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath(properties.getContextPath());
        factory.setPort(properties.getPort());
        return factory;
    }


}
