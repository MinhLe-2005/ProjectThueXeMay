package com.mycompany.smartride.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import com.mycompany.smartride.dao.AccountDAO;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/otp"})
public class GetOtpServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet otpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet otpServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String inputCode = request.getParameter("input1") + request.getParameter("input2") + request.getParameter("input3") + request.getParameter("input4") + request.getParameter("input5") + request.getParameter("input6");
        String sessionCode = (String) request.getSession().getAttribute("verificationCode");

        AccountDAO dao = AccountDAO.getInstance();

        if (inputCode.equals(sessionCode)) {

            HttpSession session = request.getSession();
            String firstname = (String) session.getAttribute("firstname");
            String lastname = (String) session.getAttribute("lastname");
            String gender = (String) session.getAttribute("gender");
            String email = (String) session.getAttribute("email");
            //        String address = (String) session.getAttribute("address");
            String phone = (String) session.getAttribute("phone");
            //        String dob = (String) session.getAttribute("dob");
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            dao.createANewAccount(firstname, lastname, gender, phone, email, username, password);
            request.setAttribute("msg", "Tạo tài khoản thành công!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Mã OTP không đúng. Vui lòng thử lại.");
            request.getRequestDispatcher("otpRegister.jsp").forward(request, response);
        }

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
