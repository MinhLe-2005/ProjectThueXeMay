/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.smartride.controller;

import com.mycompany.smartride.dao.AccountDAO;
import com.mycompany.smartride.dto.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author VoTranCongMinh
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = { "/changepassword" })
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account ac = (Account) session.getAttribute("account");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        try {
            if (ac != null) {
                if (!com.mycompany.smartride.util.PasswordUtil.checkPassword(password, ac.getPassWord())) {
                    request.setAttribute("errorPass", "Mật khẩu hiện tại không đúng.");
                } else if (password.equals(newPassword)) {
                    request.setAttribute("errorPass", "Mật khẩu hiện tại và mật khẩu cũ không được giống nhau.");
                } else if (!newPassword.equals(confirmPassword)) {
                    request.setAttribute("errorPass", "Mật khẩu mới và mật khẩu xác nhận không khớp.");
                } else if (!checkValidPass(newPassword)) {
                    request.setAttribute("errorPass",
                            "Password phải chứa ít nhất 8 ký tự, bao gồm ít nhất 1 ký tự in hoa và 1 chữ số.");
                } else {
                    boolean isChanged = AccountDAO.getInstance().changePassword(ac.getAccountId(), newPassword);
                    if (isChanged) {
                        ac.setPassWord(com.mycompany.smartride.util.PasswordUtil.hashPassword(newPassword));
                        session.setAttribute("account", ac);
                        request.setAttribute("successChange", "Thay đổi mật khẩu thành công.");
                    } else {
                        request.setAttribute("errorPass",
                                "Lỗi CSDL: Cột Password trong DB không đủ dài để chứa mã hóa BCrypt (cần ít nhất 60 ký tự). Bạn hãy vào Supabase sửa kiểu dữ liệu cột Password thành VARCHAR(255).");
                    }
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Bạn cần đăng nhập lại.');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                }
                return;
            }

            // Always forward back to the change password page so the user can see the
            // success or error message.
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException ex) {
            System.out.println(ex);
        }
    }

    private boolean checkValidPass(String pass) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        return pass != null && pass.matches(passwordRegex);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
