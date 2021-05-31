package com.github.chat.repository;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

import java.util.Collection;
import java.util.List;

public interface Repository<T> {

    T save(T entity);

    List<T> findAll();

    T findBy(T t);

    void update(T entity);

    void remove(T entity);

    default User findByEmail(User email) {
        return null;
    }
}
