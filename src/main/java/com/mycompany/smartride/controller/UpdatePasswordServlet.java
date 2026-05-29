/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.smartride.controller;

import com.mycompany.smartride.dao.AccountDAO;
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
@WebServlet(name = "UpdatePasswordServlet", urlPatterns = {"/updatepassword"})
public class UpdatePasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdatePasswordServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO dao = AccountDAO.getInstance();
//        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        int AccountID = (int) session.getAttribute("idhander");
        String password = request.getParameter("newpass");
        String confirmpassword = request.getParameter("confirmpass");
        if(password.equals(confirmpassword) && !password.isEmpty()){
            if(dao.changePassword(AccountID, password)){
                request.setAttribute("messageOke", "Mật khẩu đã được đặt lại thành công");
                request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            }
        }
        else {         
            request.setAttribute("messageError", "Mật khẩu xác nhận và Mật khẩu không trùng nhau");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int AccountID = (int) session.getAttribute("idhander");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        try {
            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("errorPass", "Mật khẩu mới và mật khẩu xác nhận không khớp.");
            } else if (!checkValidPass(newPassword)) {
                request.setAttribute("errorPass", "Password phải chứa ít nhất 8 ký tự, bao gồm ít nhất 1 ký tự in hoa và 1 chữ số.");
            } else {
                AccountDAO.getInstance().changePassword(AccountID, newPassword);
                request.setAttribute("successChange", "Thay đổi mật khẩu thành công.");
            }

            
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            
        } catch (ServletException | IOException | NumberFormatException ex) {
            System.out.println(ex);
        }
    }

    private boolean checkValidPass(String pass) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d).{8,}$";
        return pass != null && pass.matches(passwordRegex);
    }

}
