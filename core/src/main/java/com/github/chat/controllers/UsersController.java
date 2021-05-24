package com.github.chat.controllers;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.payload.Token;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static com.github.chat.utils.TransferObject.toUser;

public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final IUsersService usersService;

    public UsersController(IUsersService usersService) {
        this.usersService = usersService;
    }

    public String auth(UserAuthDto payload) {
        User user = this.usersService.findUser(toUser(payload));
        if (!Objects.isNull(user) && user.isVerification()) {
            int LIFETIME = 604800000;
            Token token = new Token(
                    user.getId(),
                    user.getNickName(),
                    System.currentTimeMillis() + LIFETIME,
                    System.currentTimeMillis()
            );
            log.info("Generate authorization token successfully!");
            return TokenProvider.encode(token);
        } else {
            return null;
        }
    }

    public String reg(UserRegDto payload) {
        User newUser = toUser(payload);
        User user = this.usersService.findUser(newUser);
        if (Objects.isNull(user)) {
            User u = this.usersService.create(toUser(payload));
            log.info("Check the User on uniq and add to db!");
            int LIFETIME = 604800000;
            Token token = new Token(
                    u.getId(),
                    u.getNickName(),
                    System.currentTimeMillis() + LIFETIME,
                    System.currentTimeMillis()
            );
            log.info("Generate authorization token successfully!");
            return TokenProvider.encode(token);
        } else {
            log.info("This User is already exist!");
            return null;
        }
    }

    public User confirmation(UserRegDto payload) {
        User user = this.usersService.findUser(toUser(payload));
        if (Objects.nonNull(user)) {
            user.setVerification(true);
            this.usersService.update(user);
            return user;
        } else {
            log.error("Not exist user!");
            return null;
        }
    }

    public void update(UserRegDto payload) {
        User newUser = toUser(payload);
        User user = this.usersService.findUser(newUser);
        if (Objects.nonNull(user)) {
            this.usersService.update(newUser);
        } else {
            log.info("This User is not exist!");
        }
    }
}
