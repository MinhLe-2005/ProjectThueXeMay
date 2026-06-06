package com.mycompany.smartride.controller;

import com.mycompany.smartride.constant.SendEmail;
import com.mycompany.smartride.dao.AccountDAO;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.util.Properties;
import java.util.UUID;
import com.mycompany.smartride.dto.Account;
import java.io.PrintWriter;

@WebServlet(name = "ForgotPassWordServlet", urlPatterns = {"/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        AccountDAO dao = AccountDAO.getInstance();
        String token = UUID.randomUUID().toString();
        String email = request.getParameter("email");
        if(dao.createToken(token, email)){
            String link = "<!DOCTYPE html>\n" +
                "<html lang=\"vi\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Đặt lại mật khẩu - SmartRide</title>\n" +
                "    <style>\n" +
                "        body { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; background-color: #f4f5f7; color: #1a1a1a; margin: 0; padding: 40px 0; }\n" +
                "        .container { max-width: 600px; margin: 0 auto; background: #ffffff; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.05); overflow: hidden; }\n" +
                "        .header { background: #1a1a1a; padding: 30px 20px; text-align: center; border-bottom: 3px solid #b59349; }\n" +
                "        .header-logo { color: #b59349; font-size: 28px; font-weight: 800; margin: 0; letter-spacing: -0.5px; }\n" +
                "        .content { padding: 40px 30px; text-align: center; }\n" +
                "        .title { font-size: 22px; font-weight: bold; margin-bottom: 20px; color: #1a1a1a; }\n" +
                "        .message { font-size: 16px; line-height: 1.6; color: #4a4a4a; margin-bottom: 30px; }\n" +
                "        .btn-reset { display: inline-block; background-color: #b59349; color: #ffffff !important; font-weight: bold; font-size: 16px; text-decoration: none; padding: 14px 32px; border-radius: 8px; margin-bottom: 30px; }\n" +
                "        .footer { padding: 20px 30px; background-color: #f9f9f9; text-align: center; font-size: 13px; color: #888888; border-top: 1px solid #eeeeee; }\n" +
                "        .footer a { color: #b59349; text-decoration: none; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <h1 class=\"header-logo\">SmartRide</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <h2 class=\"title\">Yêu cầu đặt lại mật khẩu</h2>\n" +
                "            <p class=\"message\">Chúng tôi nhận được yêu cầu đặt lại mật khẩu cho tài khoản của bạn tại <strong>SmartRide</strong>. Nếu đây là bạn, vui lòng nhấn vào nút bên dưới để tiến hành đổi mật khẩu mới.</p>\n" +
                "            <a href='http://localhost:8080/SmartRide/verify?token=" + token + "' class=\"btn-reset\">Đặt lại mật khẩu</a>\n" +
                "            <p class=\"message\" style=\"font-size: 14px; margin-bottom: 0;\">Liên kết này sẽ hết hạn sau <strong>5 phút</strong>.<br>Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này.</p>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            <p>Đội ngũ hỗ trợ khách hàng SmartRide<br>\n" +
                "            <a href=\"http://localhost:8080/SmartRide/home\">Truy cập trang chủ</a> | <a href=\"http://localhost:8080/SmartRide/forgotPassword.jsp\">Yêu cầu link mới</a></p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
            SendEmail.sendVerificationEmail(email, link);
            request.setAttribute("message", "Kiểm tra email của bạn để nhận liên kết đặt lại mật khẩu. Nếu không thấy email trong vài phút, hãy kiểm tra thư mục spam của bạn.");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }
        else{
            request.setAttribute("message", "Email không tồn tại");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }



    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
