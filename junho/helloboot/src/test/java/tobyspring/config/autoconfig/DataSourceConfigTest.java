package tobyspring.config.autoconfig;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.helloboot.HellobootApplication;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HellobootApplication.class)
class DataSourceConfigTest {
    @Autowired
    DataSource datasource;

    @Test
    void connect() throws SQLException {
        Connection connect = datasource.getConnection();
        connect.close();
    }

}