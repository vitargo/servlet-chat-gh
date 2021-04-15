package com.github.chat.controllers;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.payload.Token;

import javax.xml.crypto.Data;
import java.util.Date;

public class UsersController {

    public Token auth(UserAuthDto authDto){
        return new Token(1L, "Nane","Lname", new Date(), new Date());
    }


}
