package tobySpringBoot.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import tobySpringBoot.config.MyAutoConfiguration;

@MyAutoConfiguration
public class ServerPorpertiesConfig {
    @Bean
    public ServerProperties serverProperties(Environment env) {
        return Binder.get(env).bind("", ServerProperties.class).get();
    }
}
