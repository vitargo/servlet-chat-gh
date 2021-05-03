package com.github.chat.service;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

import java.util.List;

public interface IUsersService {

    User create(UserRegDto userRegDto);

    List<User> read();

    User update(User user);

    void delete(User user);

    User findAuth(UserAuthDto payload);

    User findReg(UserRegDto payload);

    User insert(UserRegDto payload);
}
