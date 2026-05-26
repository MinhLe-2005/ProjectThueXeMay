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
import java.util.List;

@WebServlet(name = "UpdateProfileServlet", urlPatterns = {"/updateprofile"})
public class UpdateProfileServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phonenumber");
        String userName = request.getParameter("username");
        String accountID = request.getParameter("accountID");
        HttpSession session = request.getSession();
        Account ac = (Account) session.getAttribute("account");

        try {
            if (ac != null) {
                if (isEmptyOrNull(email)) {
                    request.setAttribute("errorProfile", "Cập nhật hồ sơ thất bại! Không được để trống email.");
                } else if (!isEmptyOrNull(email) && !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                    request.setAttribute("errorProfile", "Cập nhật hồ sơ thất bại! Email chưa đúng format.");
                } else if (!isEmptyOrNull(phoneNumber) && !phoneNumber.matches("^0\\d{9}$")) {
                    request.setAttribute("errorProfile", "Cập nhật hồ sơ thất bại! Điện thoại phải có 10 số, và bắt đầu số 0.");
                } else if (!isEmptyOrNull(dob) && java.time.LocalDate.parse(dob).isAfter(java.time.LocalDate.now())) {
                    request.setAttribute("errorProfile", "Cập nhật hồ sơ thất bại! Ngày sinh không được lớn hơn ngày hiện tại.");
                } else if (AccountDAO.getInstance().checkEmailExists(email, ac.getEmail())) {
                    request.setAttribute("errorProfile", "Email đã tồn tại trước đó. Vui lòng nhập địa chỉ email khác!");
                } else if (AccountDAO.getInstance().checkPhoneNumExists(phoneNumber, ac.getPhoneNumber())) {
                    request.setAttribute("errorProfile", "Số điện thoại đã tồn tại trước đó. Vui lòng nhập số khác!");
                } else {
                    boolean success = AccountDAO.getInstance().update(firstName, lastName, gender, dob, address, phoneNumber, email, userName, Integer.parseInt(accountID));
                    if (success) {
                        session.setAttribute("account", AccountDAO.getInstance().getAccountbyID(Integer.parseInt(accountID)));
                        request.setAttribute("mess", "Cập nhật hồ sơ thành công!");
                    } else {
                        request.setAttribute("errorProfile", "Cập nhật hồ sơ thất bại!");
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
            boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
            if (isAjax) {
                response.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    if (request.getAttribute("errorProfile") != null) {
                        out.print("{\"success\": false, \"message\": \"" + request.getAttribute("errorProfile") + "\"}");
                    } else {
                        out.print("{\"success\": true, \"message\": \"" + request.getAttribute("mess") + "\"}");
                    }
                }
                return;
            }

            if (ac.getRoleID() == 1) {
                request.getRequestDispatcher("profileCustomer.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("profileStaff.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException ex) {
            System.out.println(ex);
        }
    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
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
