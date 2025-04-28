/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.gadgethub.utility;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ashmi
 */
class MyAuthenticator extends Authenticator {

    private String username, password;

    public MyAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        PasswordAuthentication pwdAuth = new PasswordAuthentication(this.username, this.password);
        return pwdAuth;
    }
}

public class JavaMailUtil {

    private static Properties properties;

    static {

        System.out.println("Preparing to send Mail");
        properties = new Properties();
        String host = "smtp.gmail.com";
        properties.put("mail.smtp.host", host);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");

    }

    protected static void sendMail(String recipient, String subject, String htmlTextMessage) throws MessagingException {
        MyAuthenticator myAuth = new MyAuthenticator(AppInfo.APP_EMAIL, AppInfo.APP_PASSWORD);
        Session session = Session.getInstance(properties, myAuth);
        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setContent(htmlTextMessage, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
