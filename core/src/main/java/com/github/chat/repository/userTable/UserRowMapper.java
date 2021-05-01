package com.github.chat.repository.userTable;

import com.github.chat.entity.User;
import com.github.micro.orm.CustomRowMapper;

public class UserRowMapper {

    public static CustomRowMapper<User> getRowMapper(){
        return resultSet -> new User(
                resultSet.getLong("id"),
                resultSet.getString("nickname"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("phone"),
                resultSet.getInt("role")
        );
    }
}
