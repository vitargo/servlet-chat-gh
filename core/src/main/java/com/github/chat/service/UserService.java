package com.github.chat.service;

import com.github.chat.controllers.UsersController;
import com.github.chat.entity.User;
import com.github.chat.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUsersService{

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UsersRepository repo;

    public static List<User> cache = new ArrayList<>();


    public UserService(UsersRepository repo) {
        this.repo = repo;
    }

    @Override
    public User create(User user) {
        User result = this.repo.save(user);
        cache.add(result);
        return result;
    }

    @Override
    public List<User> read() {
        return this.repo.findAll();
    }

    @Override
    public User findUser(User user) {
        return this.repo.findBy(user);
    }

    @Override
    public void update(User user) {
        this.repo.update(user);
    }

    @Override
    public void delete(User user) {
        this.repo.remove(user);
    }


}
