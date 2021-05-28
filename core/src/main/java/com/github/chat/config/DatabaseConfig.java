package com.github.chat.config;

import com.github.chat.repository.impl.MessageRepoImpl;
import com.github.chat.repository.impl.RoomRepoImpl;
import com.github.chat.repository.impl.UserRepoImpl;
import com.github.chat.service.IMessageService;
import com.github.chat.service.MessageServiceImpl;
import com.github.chat.service.RoomService;
import com.github.chat.service.UserService;

public class DatabaseConfig {

    public static UserService getUsersService() {
        return new UserService(new UserRepoImpl());
    }

    public static MessageServiceImpl messageService() {
        return new MessageServiceImpl(new MessageRepoImpl());
    }

    public static RoomService getRoomService() {
        return new RoomService(new RoomRepoImpl());
    }
}
