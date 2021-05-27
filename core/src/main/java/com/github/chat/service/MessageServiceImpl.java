package com.github.chat.service;

import com.github.chat.entity.Message;
import com.github.chat.repository.MessageRepository;
import com.github.chat.repository.impl.MessageRepoImpl;

import java.util.List;

public class MessageServiceImpl implements IMessageService{

    private final MessageRepoImpl repo;

    public MessageServiceImpl(MessageRepoImpl repo) {
        this.repo = repo;
    }

    @Override
    public Message save(Message message) {
        return this.repo.save(message);
    }

    @Override
    public List<Message> findAll() {
        return this.repo.findAll();
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void remove(Message message) {

    }
}
