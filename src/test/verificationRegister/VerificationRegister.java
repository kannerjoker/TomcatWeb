package test.verificationRegister;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@WebServlet(urlPatterns = "/verificationRegister")
public class VerificationRegister extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String passwd = request.getParameter("passwd");
        String verificationCode = request.getParameter("verificationCode");

        HttpSession hp = request.getSession();
        if(hp!=null&&hp.getAttribute("code")!=null){
            if(hp.getAttribute("code").equals(verificationCode)){
                GetInfoFromDatabase gfd = new GetInfoFromDatabase();
                gfd.getInfo(user,passwd);

                if(gfd.list.size()>0){
                    System.out.println("HomePage");
                    Cookie cookie = new Cookie("name",gfd.list.get(0).name);
                    cookie.setMaxAge(60*5);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    request.getServletContext().getRequestDispatcher("/vrp/HomePage.jsp").forward(request,response);
                }

            }



        }
//        request.getServletContext().getRequestDispatcher("/vrp/RegisterPage.jsp").forward(request,response);
        response.sendRedirect("/local/vrp/RegisterPage.jsp");
    }


}
