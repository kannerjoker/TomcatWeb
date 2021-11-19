import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/d1"})
public class DemoHttpRequest extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest hr1, HttpServletResponse hr2){
        System.out.println("method: " + hr1.getMethod());
        System.out.println("contextPath: " + hr1.getContextPath());
        System.out.println("servletPath: " + hr1.getServletPath());
        System.out.println("requestURI: " + hr1.getRequestURI());
        System.out.println("requestURL: " + hr1.getRequestURL());
        System.out.println("protocol: " + hr1.getProtocol());
        System.out.println("remoteAddr: " + hr1.getRemoteAddr());
        System.out.println("queryString: " + hr1.getQueryString());
    }

}
