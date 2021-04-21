package com.github.chat.service;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.repository.UsersRepository;

import java.util.List;

public class UserService implements IUsersService{

    private final UsersRepository repo;

    public UserService(UsersRepository repo) {
        this.repo = repo;
    }

    @Override
    public User create(User user) {
        return this.repo.save();
    }

    @Override
    public List<User> read() {
        return this.repo.findAll();
    }

    @Override
    public void update(long id) {
        this.repo.update(id);
    }

    @Override
    public void delete(long id) {
        this.repo.remove(id);
    }

    @Override
    public User findAuth(UserAuthDto payload) {
        return null;
    }

    @Override
    public void insert(UserRegDto payload) {

    }
}
