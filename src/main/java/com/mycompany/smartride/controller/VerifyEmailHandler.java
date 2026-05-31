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

@WebServlet(name = "VerifyEmailHandler", urlPatterns = {"/verify"})
public class VerifyEmailHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        AccountDAO dao = AccountDAO.getInstance();
        String token = request.getParameter("token");
        int AccountID = dao.getAccountIdByToken(token);
        if(AccountID != -9999) {
            HttpSession session = request.getSession();
            session.setAttribute("idhander", AccountID);
//            request.setAttribute("email", email);
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
//            try (PrintWriter out = response.getWriter()) {
//                    out.println("<html><body>");
//                    out.println("<h3>Enter new password for: " + token + "</h3>");
//                    out.println("<form action='UpdatePasswordServlet' method='POST'>");
//                    out.println("<input type='hidden' name='email' value='" + token + "'/>");
//                    out.println("<input type='password' name='newPassword' placeholder='New Password'/>");
//                    out.println("<input type='submit' value='Reset Password'/>");
//                    out.println("</form>");
//                    out.println("</body></html>");
//                }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<h3>Session has expired or is invalid</h3>");
                out.println("</body></html>");
            }
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

    }
}
