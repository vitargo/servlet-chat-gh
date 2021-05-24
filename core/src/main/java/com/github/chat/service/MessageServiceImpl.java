package com.github.chat.service;

import com.github.chat.entity.Message;
import com.github.chat.repository.MessageRepository;

import java.util.List;

public class MessageServiceImpl implements IMessageService{

    private final MessageRepository repo;

    public MessageServiceImpl(MessageRepository repo) {
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
    public List<Message> findLast30MessageByChatId(Long id) {
        return this.repo.findLast30MessageByChatId(id);
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void remove(Message message) {

    }
}
