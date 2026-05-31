package com.mycompany.smartride.constant;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author LeQuangMinh
 */
public class SendEmail {

    public static String generateRandomFourDigits() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10); // Sinh ra một số nguyên ngẫu nhiên từ 0 đến 9
            sb.append(digit); // Thêm số này vào StringBuilder
        }

        return sb.toString(); // Chuyển đổi StringBuilder thành chuỗi và trả về
    }

    public static void sendVerificationEmail(String to, String emailContent) {

        // code đăng nhập email
        final String from = "smartride.system@gmail.com";
        final String password = "gqncolnmtqoqrinr";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            msg.setSubject("SmartRide Thông Báo.");

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Nội dung email với mã xác nhận
            // String emailContent = "<h3>Hello,</h3>"
            // + "<p>To complete the registration process, please use the following OTP
            // code:</p>"
            // + "<p>OTP code: <b>" + code + ".</b></p>"
            // + "<p>If you do not require this code, please ignore the email or contact us
            // at the.color.bike.company@gmail.com</p>";
            msg.setContent(emailContent, "text/html; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi mail thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi trong quá trình gửi mail.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendVerificationEmail("lequangminhqwer@gmail.com", "Bimdiendie1@");
    }

}
