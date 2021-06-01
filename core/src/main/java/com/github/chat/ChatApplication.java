package com.github.chat;

import com.github.chat.config.ServerConfig;

import javax.servlet.ServletException;

public class ChatApplication {

    public static void main(String[] args) throws ServletException {
        int i = 0;
        ServerConfig.start().run();
    }
}
