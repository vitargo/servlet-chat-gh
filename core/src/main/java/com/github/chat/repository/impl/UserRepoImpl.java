package com.github.chat.repository.impl;

import com.github.chat.controllers.UsersController;
import com.github.chat.entity.Message;
import com.github.chat.entity.User;
import com.github.chat.repository.Repository;
import com.github.chat.utils.HibernateUtils;
import org.hibernate.Hibernate;
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
import java.util.Optional;

public class UserRepoImpl implements Repository<User> {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @Override
    public User save(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("select p from User p", User.class).list();
        }
    }

    public List<User> findById(List<Long> id){
        List<User> users = new ArrayList<>();
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            for (Long oneId:id) {
                Query<User> query = session.createQuery("select p from User p where id = :id", User.class);
                query.setParameter("id", oneId);
                if (query.uniqueResultOptional().orElse(null) != null) {
                    users.add(query.getSingleResult());
                } else {
                    System.out.println("Not found User with id = " + oneId);
                }
            }
            return users;
        }
    }

    @Override
    public User findBy(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            CriteriaQuery<User> aqu;
            if (user.getLogin() != null) {
                aqu = cr.select(root).where(cb.equal(root.get("login"), user.getLogin()));
                if (aqu == null) {
                    aqu = cr.select(root).where(cb.equal(root.get("email"), user.getEmail()));
                    if (aqu == null) {
                        aqu = cr.select(root).where(cb.equal(root.get("id"), user.getId()));
                        if (aqu == null) {
                            cr.select(root).where(cb.equal(root.get("nickName"), user.getNickName()));
                        }
                    }
                }
            } else if (user.getEmail() != null) {
                aqu = cr.select(root).where(cb.equal(root.get("email"), user.getEmail()));
                if (aqu == null) {
                    aqu = cr.select(root).where(cb.equal(root.get("id"), user.getId()));
                    if (aqu == null) {
                        cr.select(root).where(cb.equal(root.get("nickName"), user.getNickName()));
                    }
                }
            } else if (user.getId() != 0) {
                aqu = cr.select(root).where(cb.equal(root.get("id"), user.getId()));
                if (aqu == null) {
                    cr.select(root).where(cb.equal(root.get("nickName"), user.getNickName()));
                }
            } else {
                cr.select(root).where(cb.equal(root.get("nickName"), user.getNickName()));
            }
            Query<User> query = session.createQuery(cr);
            List<User> results = query.getResultList();
            if (results.size() != 0) {
                user = results.get(0);
            } else {
                user = null;
            }
            Hibernate.initialize(user);
        } catch (Exception e) {
            log.error("Enter{}:", e);
            return null;
        }
        return user;
    }

    @Override
    public User findByEmail(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("select p from User p where email = :email", User.class);
            query.setParameter("email", user.getEmail());
            return query.getSingleResult();
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
