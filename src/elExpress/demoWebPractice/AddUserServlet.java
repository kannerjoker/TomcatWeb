package elExpress.demoWebPractice;

import elExpress.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(value = "/addUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcUtils ju = new JdbcUtils();
        Connection conn = ju.getConnection();
        String paraUser = request.getParameter("user");
        String paraGender = request.getParameter("gender");
        String paraAge = request.getParameter("age");
        String paraBirth = request.getParameter("from");
        String paraQQ = request.getParameter("qq");
        String paraEmail = request.getParameter("email");

        String sql;

        Cookie[] cookies = request.getCookies();
        Cookie c = null;
        for (Cookie cookie : cookies) {
            if("RepeatUser".equals(cookie.getName())){
                c = cookie;
                c.setValue(new Date().getTime()+"");
                response.addCookie(c);
            }
        }

        try {
            sql = "select * from User where name='" + paraUser + "';";
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            boolean boo = rs.next();
            System.out.println("rs.next()-------------------->" + boo);
            if(boo){
                response.setContentType("text/html;charset=utf-8");
                Date date = new Date();
                response.sendRedirect("/local/elExpress/demoWebPractice/alertUser.jsp");
            }else{
                sql = "insert User(name,gender,age,birthPlace,qq,email) values(" +
                        "'" + paraUser + "'," + "'" + paraGender +"'," + "'" + paraAge +"'," +
                        "'" + paraBirth + "'," + "'" + paraQQ + "'," + "'" + paraEmail + "'" +
                        ");";

                conn.prepareStatement(sql).execute();
                conn.close();
                request.getServletContext().getRequestDispatcher("/listServlet").forward(request, response);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
