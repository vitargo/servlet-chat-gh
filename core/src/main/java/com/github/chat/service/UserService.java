package com.github.chat.service;

import com.github.chat.controllers.UsersController;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService implements IUsersService{

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final UsersRepository repo;

    public static List<User> cache = new ArrayList<>();


    public UserService(UsersRepository repo) {
        this.repo = repo;
    }

    @Override
    public User create(UserRegDto userRegDto) {
        User result = this.repo.save(userRegDto);
        cache.add(result);
        return result;
    }

    @Override
    public List<User> read() {
        return this.repo.findAll();
    }

    @Override
    public User update(User user) {
        return this.repo.update(user);
    }

    @Override
    public void delete(User user) {
        this.repo.remove(user);
    }

    @Override
    public User findAuth(UserAuthDto payload) {
        User result = cache.stream()
                .filter(user ->
                        user.getLogin().equals(payload.getLogin())
                                && user.getPassword().equals(payload.getPassword())
                ).findFirst().orElse(null);
        if(Objects.isNull(result)){
            result = this.repo.findAuth(payload);
            if(!Objects.isNull(result)){
                cache.add(result);
            } else {
                log.info("Invalid login or password!");
                return null;
            }
        }
        return result;
    }

    @Override
    public User findReg(UserRegDto payload) {
        User result = this.repo.findReg(payload);
        return result;
    }

    @Override
    public User insert(UserRegDto payload) {
        User result = this.repo.save(payload);
        cache.add(result);
        return result;
    }
}
