package tobyspring.config.autoconfig;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import tobyspring.config.ConditionalMyonClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyonClass("org.springframework.jdbc.core")
public class DataSourceConfig {
    @Bean
    DataSource dataSource(MyDataSourceProperties properties){
        SimpleDriverDataSource dataSourece = new SimpleDriverDataSource();


        return new SimpleDriverDataSource();
    }
}
