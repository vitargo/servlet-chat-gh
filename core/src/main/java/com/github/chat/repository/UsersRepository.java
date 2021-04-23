package com.github.chat.repository;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

import java.util.Collection;
import java.util.List;

public interface UsersRepository {

    User save(UserRegDto userRegDto);

    List<User> findAll();

    User findAuth(UserAuthDto userAuthDto);

    User findReg(UserRegDto userRegDto);

    User update(User user);

    void remove(User user);

}
