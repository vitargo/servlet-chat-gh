package com.github.chat.config;

import com.github.chat.controllers.UsersController;
import com.github.chat.entity.User;
import com.github.chat.repository.UsersRepository;
import com.github.chat.repository.impl.UserRepoImpl;
import com.github.chat.service.IUsersService;
import com.github.chat.service.UserService;
import com.github.micro.orm.CustomJdbcTemplate;

public class ControllerConfig {

    private static final CustomJdbcTemplate<User> customJdbcTemplate = new CustomJdbcTemplate<>(DatabaseConfig.getDataSource());

    private static final UsersRepository usersRepository = new UserRepoImpl(customJdbcTemplate);

    private static final IUsersService usersService = new UserService(usersRepository);

    public static UsersController usersController(){

        return new UsersController(usersService);
    }

}
