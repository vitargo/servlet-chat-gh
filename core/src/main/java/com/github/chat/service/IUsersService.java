package com.github.chat.service;

import com.github.chat.entity.User;

import java.util.List;

public interface IUsersService {

    User create(User user);

    List<User> read();

    User findUser(User user);

    void update(User user);

    void delete(User user);

}
