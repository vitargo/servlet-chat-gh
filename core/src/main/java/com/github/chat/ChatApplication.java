package com.github.chat;

import com.github.chat.config.ServerConfig;
import com.github.chat.utils.EmailSender;

import javax.servlet.ServletException;

public class ChatApplication {

    public static void main(String[] args) throws ServletException {
        EmailSender e = new EmailSender();
        e.sendEmail("rvitargo@gmail.com","TOKENTOKENTOKEEEN");
        System.out.println("Soobsheniye Poshlo");
        ServerConfig.start().run();
    }
}
