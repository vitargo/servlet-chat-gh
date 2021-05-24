package com.github.chat.config;

import com.github.chat.controllers.MessagesController;
import com.github.chat.controllers.UsersController;

public class ControllerConfig {

    public static UsersController usersController() {

        return new UsersController(DatabaseConfig.getUsersService());
    }

    public static MessagesController messagesController() {
        return new MessagesController(DatabaseConfig.messageService());
    }

}
