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

@WebServlet(value = "/deleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String paraDel = request.getParameter("del");
        JdbcUtils ju = new JdbcUtils();
        String sql = "delete from User where name='" + paraDel +"';";
        try {
            Connection conn = ju.getConnection();
            conn.prepareStatement(sql).execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getServletContext().getRequestDispatcher("/listServlet").forward(request,response);
    }
}
