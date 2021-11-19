package servletSeries;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Demo_Servlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig){
        System.out.println("init ...");
    }
    @Override
    public ServletConfig getServletConfig(){
        return null;
    }
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse){
        System.out.println("service ...");
    }
    @Override
    public String getServletInfo(){
        return null;
    }
    @Override
    public void destroy(){
        System.out.println("destroy ...");
    }
}
