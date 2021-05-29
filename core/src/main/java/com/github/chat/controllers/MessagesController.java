package com.github.chat.controllers;

import com.github.chat.entity.Message;
import com.github.chat.service.IMessageService;
import com.github.chat.service.MessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class MessagesController {

    private static final Logger log = LoggerFactory.getLogger(MessagesController.class);

    private final MessageServiceImpl messageService;

    public MessagesController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    public void saveMessage(String nickname, String message, Integer roomId) {
        this.messageService.save(new Message(roomId, nickname, message, new Date()));
    }

    public List<Message> findMessage() {
        return this.messageService.findAll();

    }
}
