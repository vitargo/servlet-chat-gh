package com.github.chat.config;

import com.github.chat.controllers.UsersController;
import com.github.chat.repository.UsersRepository;
import com.github.chat.repository.impl.UserRepoImpl;
import com.github.chat.service.IUsersService;
import com.github.chat.service.UserService;

public class ControllerConfig {

    private static UsersRepository usersRepository = new UserRepoImpl(DatabaseConfig.getDataSource());

    private static IUsersService usersService = new UserService(usersRepository);

    public static UsersController usersController(){

        return new UsersController(usersService);
    }

}
