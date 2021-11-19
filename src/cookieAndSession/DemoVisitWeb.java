package cookieAndSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns={"/demoVisitWeb"})
public class DemoVisitWeb extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if("preDateTime".equals(cookies[i].getName())){
                //对cookie解码
                response.getWriter().write("欢迎回来，您上次访问时间为:" + URLDecoder.decode(cookies[i].getValue(),"utf-8"));
                break;
            }
            if(i == cookies.length-1 && !"preDateTime".equals(cookies[i].getName())){
                response.getWriter().write("您好，欢迎您首次访问。");
                break;
            }
        }


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String preDateTime = sdf.format(date);
        //cookie中不能存空格、冒号、分号等特殊字符，需要转码后存储
        System.out.println("编码前: " + preDateTime);
        preDateTime = URLEncoder.encode(preDateTime,"utf-8");
        System.out.println("编码后: " + preDateTime);

        Cookie preVistitCookie = new Cookie("preDateTime",preDateTime);
        preVistitCookie.setMaxAge(5);
        response.addCookie(preVistitCookie);
    }
}
