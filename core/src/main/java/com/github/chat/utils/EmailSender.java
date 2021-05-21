package com.github.chat.utils;

import com.github.chat.ChatApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    Properties properties = new Properties();

    public void sendEmail(String emailRecipient, String token) {
        try {
            this.properties.load(ChatApplication.class.getClassLoader().getResourceAsStream("email.properties"));

            String email = properties.getProperty("email");
            String password = properties.getProperty("password");

            Session session = Session.getInstance(properties,
                    new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(email, password);
                        }
                    });
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(emailRecipient)
            );

            message.setSubject("Verification Email. PinkLink");
            message.setText(
                    "If you dont register in this site, please, ignore this list."
                            + "\n\nYour verification code."
                            + "https://localhost:8080/verification/" + token);

            Transport.send(message);

            System.out.println("done");

        } catch (IOException | MessagingException | NoClassDefFoundError e) {
            System.out.println(e.getMessage());
        }


    }


}
