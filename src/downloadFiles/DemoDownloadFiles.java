package downloadFiles;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns={"/demoDownloadFiles"})
public class DemoDownloadFiles extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext sc = request.getServletContext();
        String filename = request.getParameter("filename");
        String mimeType = sc.getMimeType(filename);
        String realFile = "/WEB-INF/classes/downloadFiles/img/" + filename;
        String file = sc.getRealPath(realFile);
        FileInputStream fis = new FileInputStream(file);

        response.setHeader("content-type","mimeType");
        response.setHeader("content-disposition","attachment;filename="+filename);
        int len;
        byte[] arrBytes = new byte[1024];
        ServletOutputStream sos = response.getOutputStream();
        while((len=fis.read(arrBytes))!=-1){
            sos.write(arrBytes,0,len);
        }
    }
}
