package tobyspring.config.autoconfig;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tobyspring.HellobootTest;

@HellobootTest
class DataSourceConfigTest {
    @Autowired
    DataSource datasource;

    @Test
    void connect() throws SQLException {
        Connection connect = datasource.getConnection();
        connect.close();
    }

}