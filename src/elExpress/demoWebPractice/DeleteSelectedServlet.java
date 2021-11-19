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
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet(value = "/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcUtils ju = new JdbcUtils();
        String paraStr = request.getParameter("str");

        Connection conn = ju.getConnection();
        try {
            conn.prepareStatement("delete from User where num in ("+ paraStr + ");").execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getServletContext().getRequestDispatcher("/listServlet").forward(request, response);

    }
}
