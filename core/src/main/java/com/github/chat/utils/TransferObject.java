package com.github.chat.utils;

import com.github.chat.dto.RoomRegDto;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.Room;
import com.github.chat.entity.User;

import java.util.Objects;

public class TransferObject {

    public static User toUser(UserRegDto data) {
        return new User(
                data.getId(),
                data.getNickName(),
                data.getFirstName(),
                data.getLastName(),
                data.getEmail(),
                data.getLogin(),
                data.getPassword(),
                data.getPhone(),
                data.getCompanyName(),
                data.getAvatar()
        );
    }

    public static User toUser(UserAuthDto data) {
        User user = new User();
        if(Objects.nonNull(data.getLogin())) {
            user.setLogin(data.getLogin());
        }
        if(Objects.nonNull(data.getEmail())) {
            user.setEmail(data.getEmail());
        }
        user.setPassword(data.getPassword());
        return user;
    }

    public static Room toRoom(RoomRegDto roomRegDto) {
        return new Room(roomRegDto.getNameRoom(), roomRegDto.getAdminId());
    }
}
