package test.registerPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/loginPage"})
public class ServletLogin extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        System.out.println("loginPage...");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println(user);
        UserDao dao = new UserDao();
        User theResultUser = dao.getDataInfo(user);
        if(theResultUser == null){
            //登录失败,跳转
            request.getRequestDispatcher("./loginFailed").forward(request,response);
        }else{
            //设置共享域,注意顺序(先赋值,再跳转)
            request.setAttribute("username",theResultUser);
            //登录成功,跳转
            request.getRequestDispatcher("./loginSucceed").forward(request,response);
        }
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
