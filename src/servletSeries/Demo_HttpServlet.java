package servletSeries;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns={"/servletSeries/Demo_HttpServlet"})
public class Demo_HttpServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        System.out.println("HttpServlet doGet ...");
    }
    public void doPost(HttpServletRequest httpRequest,HttpServletResponse httpResponse){
        System.out.println("HttpServlet doPost ...");
    }
}
