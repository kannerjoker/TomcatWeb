package test;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class SearchUser{
    /*public static void main(String[] args) throws Exception {
        new SearchUser().searchUser(new TestLogin_GetDatabase().getDatabase());

    }*/

    public List<Login> searchUser(DataSource dataSource){
        String sql = "select * from mysqlPractice.login;";
        JdbcTemplate jt = new JdbcTemplate(dataSource);
        List<Login> list = jt.query(sql,(ResultSet resultSet, int rowNum)->{
            Login lg = new Login();
            lg.setId(resultSet.getInt(1)) ;
            lg.setUser(resultSet.getString(2));
            lg.setPasswd(resultSet.getString(3));
            return lg;
        });
        System.out.println(list);
        return list;
    }
}
