package test.registerPage;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class UserDao {
    public UserDao(){
        System.out.println("UserDao...");
    }

    public User getDataInfo(User user){
        JdbcTemplate js = new JdbcTemplate(JdbcUtils.getDataSource());
        try {
            String sql = "select * from mysqlPractice.login where name = ? and password = ?;";
//            User theUser = js.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
            List<User> list = js.query(sql,(ResultSet rs, int row)->{
                User u = new User();
                u.setId(rs.getString(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                return u;
            },user.getUsername(),user.getPassword());
            if(list.size() == 1){
                return list.get(0);
            }else{
                //要么查询不到,要么不止一条结果
                return null;
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}