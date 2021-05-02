package com.github.chat;

import com.github.chat.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;

public class ChatApplication {

    private static final Logger log = LoggerFactory.getLogger(ChatApplication.class);

    public static void main(String[] args) throws ServletException {
        ServerConfig.start().run();

    }
}
