package test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestLogin_GetDatabase {

    public DataSource getDatabase(){
        DataSource dataSource = null;
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);

            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  dataSource;
    }
}
