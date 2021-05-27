package com.github.chat.repository;

import com.github.chat.entity.Message;
import com.github.chat.entity.User;

import java.util.List;

public interface MessageRepository {

    Message save(Message message);

    List<Message> findAll();

    void update(Message message);

    void remove(Message message);
}
