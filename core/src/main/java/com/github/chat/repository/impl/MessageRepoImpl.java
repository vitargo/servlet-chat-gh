package com.github.chat.repository.impl;

import com.github.chat.entity.Message;
import com.github.chat.repository.MessageRepository;
import com.github.chat.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<Message> findLast30MessageByChatId(Long id) {
        List<Message> messages = new ArrayList<>();
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Message> cr = cb.createQuery(Message.class);
            Root<Message> root = cr.from(Message.class);
            cr.select(root).where(cb.equal(root.get("chatId"), new Message().getChatId()));
            Query<Message> query = session.createQuery(cr);
            List<Message> results = query.getResultList();
            messages.addAll(results);
        } catch (Exception e) {
            log.error("Enter, {}", e.getMessage());
            return null;
        }
        return messages;
    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void remove(Message message) {

    }
}
