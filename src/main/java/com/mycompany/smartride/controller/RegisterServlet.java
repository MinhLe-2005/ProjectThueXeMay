package com.mycompany.smartride.controller;

import com.mycompany.smartride.constant.SendEmail;
import com.mycompany.smartride.dao.AccountDAO;
import com.mycompany.smartride.dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        AccountDAO dao = AccountDAO.getInstance();

        Account acc = dao.getAccountByEmail(email); // email đã tồn tại

        if (acc == null) { // email chưa tồn tại
            String password = request.getParameter("password");
            String confirmPass = request.getParameter("passwordConfirmation");
            if (checkValidPass(password)) { // check kí tự in hoa, số, kí tự đặc biệt
                if (password.equals(confirmPass)) { // check password == confirm pass
                    String firstname = request.getParameter("firstname");
                    String lastname = request.getParameter("lastname");
                    String gender = request.getParameter("gender");
                    String phone = request.getParameter("phone");
                    String username = request.getParameter("username");

                    if (dao.checkUsernameExist(username)) {
                        request.setAttribute("errorInput", "*Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác!");
                        request.getRequestDispatcher("register2.jsp").forward(request, response);
                        return;
                    }
                    if (dao.checkPhoneExist(phone)) {
                        request.setAttribute("errorInput", "*Số điện thoại đã được sử dụng. Vui lòng thử lại!");
                        request.getRequestDispatcher("register2.jsp").forward(request, response);
                        return;
                    }

                    if ((checkNull(firstname) || checkNull(lastname) || checkNull(gender)
                            || checkNull(phone) || checkNull(username) || checkNull(email))) {
                        request.setAttribute("errorInput", "Vui lòng nhập đúng định dạng trước khi tiếp tục.");
                        request.getRequestDispatcher("register2.jsp").forward(request, response);
                        return;
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("firstname", firstname);
                    session.setAttribute("lastname", lastname);
                    session.setAttribute("gender", gender);
                    session.setAttribute("phone", phone);
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);
                    session.setAttribute("email", email);

                    String verificationCode = SendEmail.generateRandomFourDigits();
                    session.setAttribute("verificationCode", verificationCode);
                    String emailContent = "<div style=\"font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 10px; background-color: #f9f9f9;\">"
                            + "<div style=\"text-align: center; margin-bottom: 20px;\">"
                            + "<h2 style=\"color: #b59349; margin: 0;\">SmartRide</h2>"
                            + "<p style=\"color: #777; margin: 5px 0 0 0; font-size: 14px;\">Hệ thống thuê xe chuyên nghiệp</p>"
                            + "</div>"
                            + "<div style=\"background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.05);\">"
                            + "<h3 style=\"color: #333; margin-top: 0;\">Xin chào " + firstname + " " + lastname + ",</h3>"
                            + "<p style=\"color: #555; line-height: 1.6;\">Cảm ơn bạn đã đăng ký tài khoản tại SmartRide. Để hoàn tất quá trình xác minh, vui lòng sử dụng mã OTP dưới đây:</p>"
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

                    response.sendRedirect("otpRegister.jsp");
                } else {
                    request.setAttribute("errorInput", "*Mật khẩu và xác nhận mật khẩu không bằng nhau. Vui lòng thử lại!");
                    request.getRequestDispatcher("register2.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorInput", "*Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm ít nhất 1 ký tự in hoa và 1 chữ số. Vui lòng thử lại!");
                request.getRequestDispatcher("register2.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorInput", "*Email không hợp lệ hoặc đã tồn tại. Vui lòng thử lại!"); // email đã tồn tại
            request.getRequestDispatcher("register2.jsp").forward(request, response);
        }
    }

    private boolean checkValidPass(String pass) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        return pass != null && pass.matches(passwordRegex);
    }

    private boolean checkNull(String text) {
        return text == null || text.trim().isEmpty();
    }
}
