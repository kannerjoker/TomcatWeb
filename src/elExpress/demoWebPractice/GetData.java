package elExpress.demoWebPractice;

import elExpress.JdbcUtils;
import elExpress.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetData {
    private static JdbcTemplate jt;
    private static DataSource dataSource = new JdbcUtils().getDataSource();
    static{
        jt = new JdbcTemplate(dataSource);
    }
    public List<ArrayList<String>> getData(String sql){
        List<ArrayList<String>> arrUsers = new ArrayList<>();
        List<UserInfo> users = jt.query(sql, (ResultSet rs, int num) -> {
            UserInfo u = new UserInfo();
            u.setNum(rs.getInt(1));
            u.setName(rs.getString(2));
            u.setGender(rs.getString(3));
            u.setAge(rs.getInt(4));
            u.setBirthPlace(rs.getString(5));
            u.setEmail(rs.getString(7));
            u.setQQ(rs.getString(6));
            return u;
        });

        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        for (UserInfo user : users) {
            ArrayList<String> arr = new ArrayList();
            arr.add(user.getNum()+"");
            arr.add(user.getName());
            arr.add(user.getGender());
            arr.add(user.getAge()+"");
            arr.add(user.getBirthPlace());
            arr.add(user.getQQ());
            arr.add(user.getEmail());

            arrUsers.add(arr);
//            arr.clear();
        }

        return arrUsers;
    }
}
