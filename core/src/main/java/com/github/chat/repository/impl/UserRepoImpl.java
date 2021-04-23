package com.github.chat.repository.impl;

import com.github.chat.entity.User;
import com.github.chat.repository.UsersRepository;

import javax.sql.DataSource;
import java.util.List;

public class UserRepoImpl implements UsersRepository {

    private final DataSource dataSource;

    public UserRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public User save() {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(long id) {

    }

    @Override
    public void remove(long id) {

    }
}
