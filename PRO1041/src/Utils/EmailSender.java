package Utils;

import DAO.TaiKhoanDAO;
import Entity.TaiKhoan;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;
import java.util.Random;

public class EmailSender {

    public static void sendEmail(String email, String content) {
        
        TaiKhoanDAO dao = new TaiKhoanDAO();
        String matKhau = dao.getPass(email);
      
        String host = "smtp.gmail.com";
        String username = "dorisanseanh@gmail.com";
        String accessToken = "kqtlbbncirhvphtd";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(props, null);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Thông báo từ Dịch vụ FPT POLYTECHNIC. Mật khẩu của bạn: ");
            message.setText(content +matKhau);

            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
            transport.connect(host, username, accessToken);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    } 
}
