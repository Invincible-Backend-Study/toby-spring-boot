package tobyspring.config.autoconfig;


import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyonClass("org.eclipse.jetty.server.Server")
public class JettyWebServerConfig {

    @Bean("jettyWebServerFactory")
    public JettyServletWebServerFactory servletWebServerFactory() {
        return new JettyServletWebServerFactory();
    }
}
