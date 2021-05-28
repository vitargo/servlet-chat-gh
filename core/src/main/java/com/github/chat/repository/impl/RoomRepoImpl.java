package com.github.chat.repository.impl;

import com.github.chat.entity.Room;
import com.github.chat.repository.Repository;
import com.github.chat.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomRepoImpl implements Repository<Room> {

    @Override
    public Room save(Room room) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(room);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public List<Room> findAll() {
        return null;
    }

    @Override
    public Room findBy(Room room) {
        return null;
    }

    @Override
    public void update(Room entity) {

    }

    @Override
    public void remove(Room entity) {

    }
}
