package elExpress.demoWebPractice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Encoder;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet(value = "/updateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("line");
        List<ArrayList<String>> userList = (List<ArrayList<String>>)request.getSession().getAttribute("userList");

        for (ArrayList<String> arrayList : userList) {
            if(param.equals(arrayList.get(0))){
                response.addCookie(this.addCookie("name", URLEncoder.encode(arrayList.get(1),"utf8")));
                response.addCookie(this.addCookie("gender",URLEncoder.encode(arrayList.get(2),"utf8")));
                response.addCookie(this.addCookie("age",URLEncoder.encode(arrayList.get(3),"utf8")));
                response.addCookie(this.addCookie("birth",URLEncoder.encode(arrayList.get(4),"utf8")));
                response.addCookie(this.addCookie("qq",URLEncoder.encode(arrayList.get(5),"utf8")));
                response.addCookie(this.addCookie("email",URLEncoder.encode(arrayList.get(6),"utf8")));

                break;
            }
        }
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("/local/elExpress/demoWebPractice/update.jsp");

    }
    public Cookie addCookie(String name,String param){
        Cookie c = new Cookie(name,param);
        c.setMaxAge(5*60);
        c.setPath("/");
        return c;
    }


}
