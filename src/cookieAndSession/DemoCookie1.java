package cookieAndSession;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@WebServlet(urlPatterns={"/demoCookie1"})
public class DemoCookie1 extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        this.doPost(request,response);
    }
    @DemoAnno(v1="3",v2="4",arr1={4,5,6})
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        Cookie cookie = new Cookie("msg","hello");
        cookie.setMaxAge(60*60);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        response.addCookie(cookie);

    }
    public void demoReflect(){
        DemoCookie1 dc = new DemoCookie1();
        Annotation anno = dc.getClass().getAnnotation(WebServlet.class);
        System.out.println(anno);
        int anno_s = anno.toString().indexOf("(");
        int anno_e = anno.toString().indexOf(")");
        String sub = anno.toString().substring(anno_s + 1 , anno_e);
        System.out.println("sub -->" + sub);
        System.out.println("anno -->" + anno);
        Method method = null;
        try {
            method = DemoCookie1.class.getDeclaredMethod("doPost",HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(method);
        if(method.isAnnotationPresent(DemoAnno.class)){
            System.out.println("DemoAnno ...!");
        }else{
            System.out.println("...");
        }

        DemoAnno da = method.getAnnotation(DemoAnno.class);
        System.out.println(da.v1());
        System.out.println(da.v2());
        System.out.println(Arrays.toString(da.arr1()));
    }
}
