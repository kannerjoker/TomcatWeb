package test.verificationRegister;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class GetInfoFromDatabase {
    List<User> list = null;
    @Test
    public void getInfo(String user,String passwd){
        try {
            String url = "jdbc:sqlite:" + GetInfoFromDatabase.class.getClassLoader().getResource("userInfo.sqlite").getFile();

            Properties pro = new Properties();
            pro.setProperty("driverClassName","org.sqlite.JDBC");
            pro.setProperty("url",url);
            pro.setProperty("username","");
            pro.setProperty("password","");
            DataSource dataSource = DruidDataSourceFactory.createDataSource(pro);
//            Connection conn = dataSource.getConnection();

            JdbcTemplate jt = new JdbcTemplate(dataSource);
//            User user = jt.queryForObject("select * from userInfo;", User.class, "name", "passwd");
            /*String sql = "select * from userInfo;";
            List<User> users = jt.query(sql,new BeanPropertyRowMapper(User.class));*/



            list = jt.query("select * from userInfo where name='" + user +"' and passwd='" + passwd +"';",new RowMapper(){
                @Override
                public User mapRow(ResultSet rs,int row){
                    User u = new User();
                    try {
                        u.setId(rs.getString(1));
                        u.setName(rs.getString(2));
                        u.setPasswd(rs.getString(3));
                        u.setNum(rs.getString(4));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return u;
                }
            });

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
