package elExpress.demoWebPractice;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/listLabelServlet")
public class ListLabelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //记录选中的标签
        ServletContext servletContext = request.getServletContext();
        //分页最终实在list页面显示,如果只在此处定义,list页面不通过此分支的操作,会导致label不更新
        /*Integer paraLabel = Integer.valueOf(request.getParameter("label"));
        int formSize = (Integer)request.getSession().getAttribute("formSize");
        String sql = "select * from User limit " + (paraLabel-1)*formSize + "," + formSize + ";";
        servletContext.setAttribute("sql" ,sql);
        request.getSession().setAttribute("label",paraLabel+"");*/
        servletContext.getRequestDispatcher("/listServlet").forward(request,response);
    }
}
