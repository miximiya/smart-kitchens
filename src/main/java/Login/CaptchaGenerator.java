package Login;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
//二维码验证
@WebServlet("/captcha")
public class CaptchaGenerator extends HttpServlet {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        generateCaptcha(request);

        // 创建一个图像缓冲区对象
        BufferedImage captchaImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文对象
        Graphics2D g = captchaImage.createGraphics();

        // 设置背景色为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 获取会话中的验证码
        String captchaText = (String) request.getSession().getAttribute("captcha");

        // 绘制验证码
        Font font = new Font("Arial", Font.BOLD, 20);
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.1);
        Font modifiedFont = font.deriveFont(attributes);
        g.setFont(modifiedFont);
        g.setColor(Color.BLACK);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawString(captchaText, 10, 30);

        // 添加干扰线和噪点...

        // 设置响应内容类型为图片
        response.setContentType("image/png");

        // 获取输出流，将验证码图片写入响应中
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(captchaImage, "png", outputStream);
        outputStream.close();
    }

    private void generateCaptcha(HttpServletRequest request) {
        // 生成随机验证码
        String captchaText = generateRandomCaptcha();

        // 将验证码保存到Session中
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captchaText);
    }

    private static String generateRandomCaptcha() {
        // 生成包含数字和字母的随机验证码
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
