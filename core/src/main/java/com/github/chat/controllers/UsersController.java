package com.github.chat.controllers;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.payload.Token;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.TokenProvider;

import java.util.Date;

public class UsersController {

    private final IUsersService usersService;

    public UsersController(IUsersService usersService) {
        this.usersService = usersService;
    }

    public String auth(UserAuthDto payload){
        User user = this.usersService.findAuth(payload);
        Token token = new Token(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                new Date(System.currentTimeMillis() + 1800000).getTime(),
                new Date().getTime()
        );
        return TokenProvider.encode(token);
    }

    public void reg(UserRegDto payload) {
//  TODO check if exist;
        this.usersService.insert(payload);
    }


}
