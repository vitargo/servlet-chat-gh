package com.github.chat.repository.impl;

import com.github.micro.orm.CustomJdbcTemplate;

public class UserRepoImpl {

    private final CustomJdbcTemplate customJDBCTemplate;

    public UserRepoImpl(CustomJdbcTemplate customJDBCTemplate) {
        this.customJDBCTemplate = customJDBCTemplate;
    }




}
