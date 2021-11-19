package elExpress.demoWebPractice;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/searchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("searchUser");
        String birth = request.getParameter("searchBirth");
        String email = request.getParameter("searchEmail");
        //将查询语句根据参数分块
        String searchSql = "select * from User";
        String step1 = " where";
        String step2 = " and";
        String step3 = ";";
        String seU = "";
        String seB = "";
        String seE = "";
        //组装参数字符
        if(user != ""){
            seU = " name='" + user + "'";
        }
        if(birth != ""){
            seB = " birthPlace='" + birth + "'";
        }
        if(email != ""){
            seE = " email='" + email + "'";
        }
        //由于不知道哪些参数为空,将参数放进数组,通过空字符串数组来判定参数最终组装效果
        String[] arr = new String[]{seU,seB,seE};
        int count = 0;
        //查看参数不为空个数
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!="")
                count++;
        }
        //参数全为空,不需要带参数
        if(count==0){
            searchSql += step3;
        }
        //有一个不为空
        if(count==1){
            for (int i = 0; i < arr.length; i++) {
                if(arr[i]!="") {
                    searchSql += step1 + arr[i];
                    break;          //省略语句中走到break,会直接跳出servlet服务,break需放到{}
                }
            }
            //最后组装";"
            searchSql += step3;
        }
        //有2个为空
        int num = 0;        //通过num来查看组装到了第几个参数
        if(count==2){
            for (int i = 0; i < arr.length; i++) {
                if(arr[i]=="") {  //为空就跳过循环
                    continue;
                }
                num++;
                if(num==1)
                    searchSql += step1 + arr[i];    //组装第一个,需要带"where"
                else
                    searchSql += step2 + arr[i];    //后面组装需要带上"and"
            }
            searchSql += step3;                     //最后组装带上";"
        }
        //有3个为空
        num =0;
        if(count==3){
            for (int i = 0; i < arr.length; i++) {
                num++;
                if(num==1)
                    searchSql += step1 + arr[i];
                else
                    searchSql += step2 + arr[i];
            }
            searchSql += step3;
        }

//        System.out.println("searchSql: " + searchSql);
        HttpSession session = request.getSession();
        if(session.getAttribute("label")!=null){
            session.setAttribute("label","1");
        }
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("searchSql",searchSql);
        servletContext.setAttribute("user",user);
        servletContext.setAttribute("birth",birth);
        servletContext.setAttribute("email",email);

        servletContext.getRequestDispatcher("/listServlet").forward(request, response);
    }
}
