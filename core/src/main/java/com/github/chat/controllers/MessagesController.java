package com.github.chat.controllers;

import com.github.chat.entity.Message;
import com.github.chat.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MessagesController {

    private static final Logger log = LoggerFactory.getLogger(MessagesController.class);

    private final IMessageService messageService;

    public MessagesController(IMessageService messageService) {
        this.messageService = messageService;
    }

    public void saveMessage(String message) {
        this.messageService.save(new Message(1, 1, 1L, message, new Date()));
    }
}
