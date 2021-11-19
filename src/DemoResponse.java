import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet()
public class DemoResponse extends HttpServlet {
    public static void main(String[] args) {

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
//        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        try {
//            response.sendRedirect("https://tieba.baidu.com/index.html");
            /*PrintWriter pw = response.getWriter();
            pw.write("<h1>hello word! 你好,java!</h1>");*/
            BufferedImage bufferedImage = new BufferedImage(150,60,BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(Color.pink);
            int width = 150;
            int height = 60;
            graphics.fillRect(0,0,width,height);
            graphics.setColor(Color.white);
            graphics.drawRect(0,0,width-1,height-1);
            graphics.setColor(Color.blue);
            String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                String s = String.valueOf(str.charAt(random.nextInt(str.length())));
                graphics.drawString(s,(width-40)/4*(i+1),height/2);
            }
            ImageIO.write(bufferedImage,"jpg",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response){
        this.doPost(request,response);
    }
}
