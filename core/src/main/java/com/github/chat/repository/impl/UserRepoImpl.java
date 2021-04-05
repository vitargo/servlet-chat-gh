package com.github.chat.repository.impl;

import com.github.micro.orm.CustomJDBCTemplate;

public class UserRepoImpl {

    private final CustomJDBCTemplate customJDBCTemplate;

    public UserRepoImpl(CustomJDBCTemplate customJDBCTemplate) {
        this.customJDBCTemplate = customJDBCTemplate;
    }




}
