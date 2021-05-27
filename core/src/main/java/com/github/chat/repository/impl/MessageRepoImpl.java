package com.github.chat.repository.impl;

import com.github.chat.entity.Message;
import com.github.chat.repository.MessageRepository;
import com.github.chat.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MessageRepoImpl implements MessageRepository {

    private static final Logger log = LoggerFactory.getLogger(MessageRepoImpl.class);

    @Override
    public Message save(Message message) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(message);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            log.info("Enter, {}", e.getMessage());
        }
        return message;
    }

    @Override
    public List<Message> findAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("select p from Message p", Message.class).list();
        }
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void remove(Message message) {

    }
}
