package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Driver;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.config.ConditionalMyonClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@ConditionalMyonClass("org.springframework.jdbc.core.JdbcOperations")
@EnableTransactionManagement
public class DataSourceConfig {


    @Bean
    @ConditionalMyonClass("com.zaxxer.hikari.HikariDataSource")
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        HikariDataSource dataSourece = new HikariDataSource();
        dataSourece.setDriverClassName(properties.getDriverClassName());
        dataSourece.setJdbcUrl(properties.getUrl());
        dataSourece.setUsername(properties.getUserName());
        dataSourece.setPassword(properties.getPassword());
        System.out.println(dataSourece.getJdbcUrl());
        return dataSourece;
    }

    @Bean
    @ConditionalOnMissingBean
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSourece = new SimpleDriverDataSource();
        dataSourece.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSourece.setUrl(properties.getUrl());
        dataSourece.setUsername(properties.getUserName());
        dataSourece.setPassword(properties.getPassword());

        return dataSourece;
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }


}
