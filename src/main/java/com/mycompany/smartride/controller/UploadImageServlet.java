/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.smartride.controller;

import com.mycompany.smartride.dao.AccountDAO;
import com.mycompany.smartride.dto.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import com.mycompany.smartride.util.SupabaseStorageUtil;

/**
 *
 * @author LeQuangMinh
 */
@WebServlet(name = "UploadImageServlet", urlPatterns = {"/uploadimage"})
@MultipartConfig // Thêm annotation này
public class UploadImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private FileUploaded fileUploadHandler;

    @Override
    public void init() throws ServletException {
        fileUploadHandler = new FileUploaded("D:\\ailaithuexeha\\MotorcycleRental\\src\\main\\webapp\\images");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("profileCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            if (account == null) {
                response.sendRedirect("login.jsp");  // Redirect to login if session is invalid
                return;
            }
            String id = request.getParameter("id");

            String name = "imageAcc" + id + generateBookingCode() + ".jpg";
            Part filePart = request.getPart("file");
            String publicUrl = fileUploadHandler.handleFileUpload(filePart, name, "profileImg");
            if (publicUrl == null) publicUrl = name;

            // Delete old image if exists
            if (account.getImage() != null && !account.getImage().isEmpty()) {
                SupabaseStorageUtil.deleteFile(account.getImage());
            }

            AccountDAO dao = AccountDAO.getInstance();
            dao.updateProfileImage(Integer.parseInt(id), publicUrl);
            
            // Cập nhật thuộc tính image trong đối tượng account
            account.setImage(publicUrl);

            // Cập nhật lại đối tượng account trong session
            session.setAttribute("account", account);

            
      
        } catch (Exception e) {
            System.out.println(e);
        }
//        if (filePart != null && filePart.getSize() > 0) {
//            String fileName = fileUploadHandler.generateNewFileName(fileUploadHandler.getFileName(filePart));
//            String filePath = fileUploadHandler.handleFileUpload(filePart, fileName);
//            Account account = (Account) session.getAttribute("account");
//            if (filePath != null) {
//                // Update the database with the new file path
//                AccountDAO.getInstance().updateProfileImage(account.getAccountId(), filePath);
//
//                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write("{\"success\": true, \"filePath\": \"" + filePath + "\"}");
//            } else {
//                response.setContentType("application/json");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write("{\"success\": false}");
//            }
//        } else {
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write("{\"success\": false, \"message\": \"No file uploaded\"}");
//        }
    }
    
    private String generateBookingCode() {
          //Khởi tạo một đối tượng Random
        Random random = new Random();

        // Sinh ra 6 số ngẫu nhiên từ 0 đến 999999
        int randomNumber = random.nextInt(1000000);

        // Format số ngẫu nhiên thành chuỗi, thêm vào "BOOK"
        String bookingCode = String.format("%06d", randomNumber);

        return bookingCode;
    }
}