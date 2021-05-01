package com.github.chat.repository.impl;

import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;

public class UserRepoImplMocks {

    public static final long USER_INSERT_DATA_ID = 1L;

    public static final String USER_INSERT_FIRST_NAME = "One";

    public static final String USER_INSERT_LAST_NAME = "First";

    public static final String USER_INSERT_NICKNAME = "NumberOne";

    public static final String USER_INSERT_EMAIL = "one@gmail.com";

    public static final String USER_INSERT_LOGIN = "onef";

    public static final String USER_INSERT_PASSWORD = "12345";

    public static final String USER_INSERT_PHONE = "11111111111";

    public static final int USER_INSERT_ROLE = 2;

    public static UserRegDto insertData() {
        return new UserRegDto (
                USER_INSERT_DATA_ID,
                USER_INSERT_NICKNAME,
                USER_INSERT_FIRST_NAME,
                USER_INSERT_LAST_NAME,
                USER_INSERT_EMAIL,
                USER_INSERT_LOGIN,
                USER_INSERT_PASSWORD,
                USER_INSERT_PASSWORD,
                USER_INSERT_PHONE,
                USER_INSERT_ROLE
        );
    }

    public static User expectedInsertData() {
        return new User (
                USER_INSERT_DATA_ID,
                USER_INSERT_NICKNAME,
                USER_INSERT_FIRST_NAME,
                USER_INSERT_LAST_NAME,
                USER_INSERT_EMAIL,
                USER_INSERT_LOGIN,
                USER_INSERT_PASSWORD,
                USER_INSERT_PHONE,
                USER_INSERT_ROLE
        );
    }


}
