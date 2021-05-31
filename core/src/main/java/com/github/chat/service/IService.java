package com.github.chat.service;

import com.github.chat.entity.User;

import java.util.List;

public interface IService<T> {

    T create(T user);

    List<T> read();

    T findUser(T entity);

    void update(T entity);

    void delete(T entity);

    default User findByEmail(User user) {
        return null;
    }
}
