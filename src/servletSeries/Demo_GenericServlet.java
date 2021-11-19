package servletSeries;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Demo_GenericServlet extends GenericServlet {
    @Override
    public void service(ServletRequest var1, ServletResponse var2){
        System.out.println("GenericServlet is an abstract class...");
    }
}
