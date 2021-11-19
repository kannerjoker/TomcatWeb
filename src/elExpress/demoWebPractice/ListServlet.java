package elExpress.demoWebPractice;

import elExpress.test.PrePareTestSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/listServlet")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int labelCount;
        int formSize = 5;

        GetData gd = new GetData();
        List<ArrayList<String>> data;
        String conSql = this.setConditionSelection(request);
        data = gd.getData(conSql);
        //条件查询完成,分页就标签\大小等就已经确定了
        this.configSession(request,data,formSize);
        String pageSql = this.setPagination(request,conSql,formSize);
        System.out.println("pageSql: " + pageSql);
        data = gd.getData(pageSql);


        //设置分页显示列表,列表需要根据条件筛选和所选标签来定,故最后设置
        HttpSession session = request.getSession();
        session.setAttribute("userList",data);


//        request.getServletContext().getRequestDispatcher("/elExpress/demoWebPractice/list.jsp").forward(request,response);
        response.setContentType("text/html;charset=utf-8");
        response.sendRedirect("http://localhost:8080/local/elExpress/demoWebPractice/list.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
    //条件查询
    public  String setConditionSelection(HttpServletRequest request){
        String sql;
        Object searchSql = request.getServletContext().getAttribute("searchSql");
        //是否有条件查询
        if(searchSql==null){
            sql = "select * from User;";
        }else{
            sql = searchSql+"";
        }
        searchSql = null;
        return sql;
    }
    //分页切换
    public String setPagination(HttpServletRequest request,String conditionSql,int formSize){
        String seSql;           //seSql用来组装条件查询和分页显示

        String temp = conditionSql.split(";")[0];    //去掉";"

        //通过选中标签来确定分页
        Object label = request.getParameter("label");

        if(label!=null){
            request.getSession().setAttribute("label",label);       //存储选中的分页
            Integer paraLabel = Integer.valueOf(label+"");
            String sql = (paraLabel-1)*formSize + "," + formSize + ";";
            seSql = temp + " limit " + sql;          //根据条件查询,加上sql分页条件组装查询语句
        }else{
            seSql = temp + " limit " + formSize + ";";      //未设置分页信息时,默认查询/显示前面n条
        }
        return seSql;
    }
    //定义session,展现到页面
    public void configSession(HttpServletRequest request,List<ArrayList<String>> data,int formSize){
        //设置分页信息
        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(60*60);

        int labelCount = (data.size()-data.size()%formSize)/formSize;
        if(data.size()%formSize != 0){
            labelCount += 1;
        }
        List<Integer> labelList = new ArrayList();
        for (int i = 0; i < labelCount; i++) {
            labelList.add(i + 1);
        }
        session.setAttribute("userListSize",data.size());
        session.setAttribute("formSize",formSize);
        session.setAttribute("labelList",labelList);

        //更新选中标签,防止超出边界
        Object label = session.getAttribute("label");
        if(label!=null&&Integer.valueOf(label+"")>labelList.size()){
            session.setAttribute("label",labelList.size());
        }
    }
}
