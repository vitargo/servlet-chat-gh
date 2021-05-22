package com.github.chat.utils;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

public class TransferObject {

    public static User toUser(UserRegDto data) {
        return new User(
                data.getNickName(),
                data.getFirstName(),
                data.getLastName(),
                data.getEmail(),
                data.getLogin(),
                data.getPassword(),
                data.getPhone(),
                data.getCompanyName()
        );
    }

    public static User toUser(UserAuthDto data) {
        return new User(
                data.getLogin(),
                data.getPassword()
        );
    }
}
