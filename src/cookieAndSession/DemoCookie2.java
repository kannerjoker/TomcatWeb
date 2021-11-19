package cookieAndSession;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@WebServlet(urlPatterns={"/demoCookie2"})
public class DemoCookie2 extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        this.doPost(request,response);
    }
    @DemoAnno(v1="3",v2="4",arr1={4,5,6})
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            System.out.println(cookies[i].getName() + ":" + cookies[i].getValue());
        }


    }
    public void demoReflect(){
        DemoCookie2 dc = new DemoCookie2();
        Annotation anno = dc.getClass().getAnnotation(WebServlet.class);
        System.out.println(anno);
        int anno_s = anno.toString().indexOf("(");
        int anno_e = anno.toString().indexOf(")");
        String sub = anno.toString().substring(anno_s + 1 , anno_e);
        System.out.println("sub -->" + sub);
        System.out.println("anno -->" + anno);
        Method method = null;
        try {
            method = DemoCookie2.class.getDeclaredMethod("doPost",HttpServletRequest.class,HttpServletResponse.class);
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
