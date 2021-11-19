package elExpress.demoWebPractice;

import elExpress.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(value = "/reDataServlet")
public class ReDataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcUtils ju = new JdbcUtils();
        Connection conn = ju.getConnection();
        String paraName = request.getParameter("user");
        String paraGender = request.getParameter("gender");
        String paraAge = request.getParameter("age");
        String paraBirth = request.getParameter("from");
        String paraQQ = request.getParameter("qq");
        String paraEmail = request.getParameter("email");

        String sql = "update User set " +
                "                       gender='" + paraGender +" '," +
                                          "age=' " +  paraAge + "'," +
                                   "birthPlace='" + paraBirth + "'," +
                "                           qq='" + paraQQ + "'," +
                "                        email='" + paraEmail +"' " +
                     "where " +
                                        "name='" + paraName +"';";
        try {
            conn.prepareStatement(sql).execute();
            Thread.sleep(500);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        request.getServletContext().getRequestDispatcher("/listServlet").forward(request,response);
    }
}
