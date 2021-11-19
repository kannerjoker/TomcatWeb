package test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

@WebServlet(urlPatterns={"/login"})
public class TestLogin_GetInfo extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("doPost ...");
        String user;
        String passwd;
        Enumeration<String> names = request.getParameterNames();
        ArrayList<String> arrNames = new ArrayList();
        while(names.hasMoreElements()){
            arrNames.add(names.nextElement());
        }
        user = request.getParameter(arrNames.get(0));
        passwd = request.getParameter(arrNames.get(1));
        System.out.println("user: " + user);
        System.out.println("passwd: " + passwd);
        //设置共享域
        /*request.setAttribute("user",request.getParameter(user));
        request.setAttribute("passwd",request.getParameter(passwd));*/
        //设置转发
//        request.getRequestDispatcher("./searchUser").forward(request,response);
        //判断
        TestLogin_GetDatabase tlgd = new TestLogin_GetDatabase();
        List<Login> list = new SearchUser().searchUser(tlgd.getDatabase());
        for (int i = 0; i < list.size(); i++) {
            if(user == null){
                return;
            }
            if(user.equals(list.get(i).getUser())){
                if(passwd.equals(list.get(i).getPasswd())){
                    System.out.println("验证通过!");

                }else{
                    System.out.println("密码错误!");
                }
            }else{
                System.out.println("用户名不存在!");

            }
        }
    }
}
