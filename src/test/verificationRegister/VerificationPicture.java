package test.verificationRegister;

import sun.print.ProxyGraphics;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet(urlPatterns="/verificationPicture")
public class VerificationPicture extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        this.doPost(request,response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            ServletOutputStream sos = response.getOutputStream();
            int width = 150;
            int height = 50;
            BufferedImage bufferedImage = new BufferedImage(150,50,BufferedImage.TYPE_INT_RGB);
            Graphics graphics = bufferedImage.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0,0,width,height);
            graphics.setColor(Color.black);
            graphics.drawRect(0,0,width-1,height-1);
            graphics.setColor(Color.red);
            Random ran = new Random();
            String code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            String vc = "";
            for (int i = 0; i < 4; i++) {
                int num = ran.nextInt(code.length());
                int x = (width-30)/4*(i+1);
                int y = height/2;
                graphics.drawString(code.charAt(num)+"",x,y);
                vc += code.charAt(num);
            }
            HttpSession hs = request.getSession();
            hs.setAttribute("code",vc);
            ImageIO.write(bufferedImage,"jpg",sos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
