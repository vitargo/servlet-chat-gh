package com.github.chat.controllers;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.payload.Token;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;

public class UsersController {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final IUsersService usersService;

    public UsersController(IUsersService usersService) {
        this.usersService = usersService;
    }

    public String auth(UserAuthDto payload){
        User user = this.usersService.findAuth(payload);
        if (!Objects.isNull(user)){
            Token token = new Token(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    new Date(System.currentTimeMillis() + 1800000).getTime(),
                    new Date().getTime()
            );
            log.info("Generate cipher token!");
            return TokenProvider.encode(token);
        } else {
            return null;
        }
    }

    public boolean reg(UserRegDto payload) {
        User user = this.usersService.findReg(payload);
        if(Objects.isNull(user)) {
            this.usersService.insert(payload);
            log.info("Check the User on uniq and add to db!");
            return true;
        } else {
            log.info("This User is already exist!");
            return false;
        }
    }
}
