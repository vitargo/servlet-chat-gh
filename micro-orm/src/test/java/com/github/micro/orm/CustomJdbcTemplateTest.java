package com.github.micro.orm;

import com.github.chat.config.DatabaseConfig;
import com.github.chat.entity.User;
import com.github.micro.orm.impl.UserRowMapper;
import org.junit.Test;

import java.util.Collection;

public class CustomJdbcTemplateTest {

    CustomJdbcTemplate jdbcTemplate = new CustomJdbcTemplate(new DatabaseConfig());
    UserRowMapper rowMapper = new UserRowMapper();

    @Test
    public void findAll() {
        Collection<User> act = jdbcTemplate.findAll("SELECT * FROM user", rowMapper);
        System.out.println(act + "jj");
    }

}