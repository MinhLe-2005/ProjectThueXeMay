package com.mycompany.smartride.controller;

import com.mycompany.smartride.constant.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author LeQuangMinh
 */
@WebServlet(name = "reOtpServlet", urlPatterns = {"/reOtp"})
public class GetReOtpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        // Generate verification code
        String verificationCode = SendEmail.generateRandomFourDigits();
        // Save verification code in session
        session.setAttribute("verificationCode", verificationCode);
        // Send verification email
        String emailContent = "<div style=\"font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; background-color: #f9f9f9;\">"
                + "<div style=\"text-align: center; margin-bottom: 20px;\">"
                + "<h2 style=\"color: #b59349; margin: 0;\">SmartRide</h2>"
                + "<p style=\"color: #777; margin: 5px 0 0 0; font-size: 14px;\">Hệ thống thuê xe chuyên nghiệp</p>"
                + "</div>"
                + "<div style=\"background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.05);\">"
                + "<h3 style=\"color: #333; margin-top: 0;\">Xin chào,</h3>"
                + "<p style=\"color: #555; line-height: 1.6;\">Chúng tôi nhận được yêu cầu gửi lại mã OTP của bạn. Vui lòng sử dụng mã mới dưới đây để hoàn tất quá trình xác minh:</p>"
                + "<div style=\"text-align: center; margin: 30px 0;\">"
                + "<span style=\"display: inline-block; padding: 15px 30px; font-size: 24px; font-weight: bold; color: #b59349; background-color: #fdfaf3; border: 2px dashed #b59349; border-radius: 8px; letter-spacing: 5px;\">" + verificationCode + "</span>"
                + "</div>"
                + "<p style=\"color: #888; font-size: 13px; line-height: 1.5; text-align: justify;\">Mã OTP này chỉ có hiệu lực trong thời gian ngắn và chỉ sử dụng một lần. Vui lòng không chia sẻ mã này cho bất kỳ ai. Nếu bạn không thực hiện yêu cầu này, vui lòng bỏ qua email này hoặc liên hệ hỗ trợ.</p>"
                + "</div>"
                + "<div style=\"text-align: center; margin-top: 20px; color: #aaa; font-size: 12px;\">"
                + "<p>&copy; 2026 SmartRide System. Mọi quyền được bảo lưu.</p>"
                + "<p>Hỗ trợ: smartride.system@gmail.com</p>"
                + "</div>"
                + "</div>";
        SendEmail.sendVerificationEmail(email, emailContent);
        // Redirect to the confirmation page

        response.sendRedirect("otpRegister.jsp");
    }

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


}