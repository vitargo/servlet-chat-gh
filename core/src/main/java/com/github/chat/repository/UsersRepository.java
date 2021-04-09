package com.github.chat.repository;

import com.github.chat.entity.User;

import java.util.List;

public interface UsersRepository {

    User save();

    List<User> findAll();

    void update(long id);

    void remove(long id);

}
