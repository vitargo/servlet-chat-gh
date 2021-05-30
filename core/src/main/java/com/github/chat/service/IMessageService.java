package com.github.chat.service;

import com.github.chat.entity.Message;

import java.util.List;

public interface IMessageService {

    Message save(Message message);

    List<Message> findAll();

    List findByRoom(int id);

    void update(Message message);

    void remove(Message message);
}
