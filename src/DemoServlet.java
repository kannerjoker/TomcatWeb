import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@WebServlet(urlPatterns = "/DemoServlet")
public class DemoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        methodForGetAndPost(request);


        //设置共享域
        /*
            *一般用于请求转发多个资源时共享数据
            * 方法:
                * setAttribute(String key,Object obj)
                * getAttribute(String key)
                * removeAttribute(String key)
        */
        request.setAttribute("msg_text",request.getParameter("text"));

        //请求转发
        /*
            特点:
                * 地址不会变化
                * 只能访问服务器本地资源
                * 只请求一次
        */
        request.getRequestDispatcher("/servletSeries/Demo_HttpServlet").forward(request,response);
        request.removeAttribute("msg_text");
        request.getRequestDispatcher("/servletSeries/Demo_GenericServlet").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        methodForGet(request);
    }
    static void methodForGet(HttpServletRequest request){
        String method = request.getMethod();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        request.getQueryString();
        request.getProtocol();
        request.getRemoteAddr();
        Enumeration<String> it = request.getHeaderNames();
        while (it.hasMoreElements()) {
            String str = it.nextElement();
            System.out.println(str + " ---> " + request.getHeader(str));
        }
    }
    static void methodForGetAndPost(HttpServletRequest request){
        //设置编码格式: setCharacterEncoding("utf-8");
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Enumeration<String> enuNames = request.getParameterNames();
        System.out.println("getParameterNames() ----------------->");
        while (enuNames.hasMoreElements()) {
            System.out.println(enuNames.nextElement());
        }
        enuNames = request.getParameterNames();
        System.out.println("getParameter() ----------------->");
        while(enuNames.hasMoreElements()){
            System.out.println(request.getParameter(enuNames.nextElement()));
        }
        enuNames = request.getParameterNames();
        System.out.println("getParameterValues() ----------------->");
        while(enuNames.hasMoreElements()){
            String[] strs = request.getParameterValues(enuNames.nextElement());
            System.out.println(Arrays.toString(strs));
        }
        enuNames = request.getParameterNames();
        System.out.println("getParameterMap() ----------------->");
        Map<String,String[]> paraMap = request.getParameterMap();
        for (String s : paraMap.keySet()) {
            String[] strs = paraMap.get(s);
            System.out.println(Arrays.toString(strs));
        }
    }
}
