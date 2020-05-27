package image;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/image")
public class ResponseImageServlet extends HttpServlet {


    private static class Imagination {
        private static BufferedImage drawGraphics() {
            BufferedImage imgBuff = new BufferedImage(500, 200, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = imgBuff.createGraphics();
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            g2.setColor(Color.BLUE);
            g2.drawString("This is servlet, MF!", 110, 110);
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(1.0f,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND,
                    10.0f, new float[]{10.0f}, 0.0f));
            g2.drawRect(30,30,50,50);
            g2.setStroke(new BasicStroke());
            g2.setColor(Color.WHITE);
            g2.drawOval(70,30, 30,30);

            g2.setPaint(new GradientPaint(250,150,Color.RED,350, 150,Color.GRAY));
            g2.fill (new Ellipse2D.Double(250, 150, 100, 50));
            g2.dispose();
            return imgBuff;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");
        try(ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(Imagination.drawGraphics(), "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }
}

