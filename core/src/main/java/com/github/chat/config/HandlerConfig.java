package com.github.chat.config;

import com.github.chat.handlers.UsersHandler;

public class HandlerConfig {
    public static UsersHandler usersHandler(){
        return new UsersHandler(ControllerConfig.usersController());
    };


}
