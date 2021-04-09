package com.github.chat.service;

import com.github.chat.entity.User;

import java.util.List;

public interface IUsersService {

    User create(User user);

    List<User> read();

    void update(long id);

    void delete(long id);

}
