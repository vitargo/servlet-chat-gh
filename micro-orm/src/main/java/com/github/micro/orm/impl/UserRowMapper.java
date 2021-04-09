package com.github.micro.orm.impl;

import com.github.chat.entity.User;
import com.github.micro.orm.CustomRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements CustomRowMapper {
    @Override
    public User rowMap(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setRole(rs.getInt("role"));

        return user;
    }
}
