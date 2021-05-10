package com.github.chat.repository.impl;

import com.github.chat.controllers.UsersController;
import com.github.chat.entity.User;
import com.github.chat.exceptions.LoginException;
import com.github.chat.repository.UsersRepository;
import com.github.chat.repository.userTable.UserRowMapper;
import com.github.chat.repository.userTable.UserTable;
import com.github.micro.orm.CustomJdbcTemplate;
import com.github.micro.orm.exceptions.CustomSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserRepoImpl implements UsersRepository {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private final CustomJdbcTemplate<User> customJdbcTemplate;

    public UserRepoImpl(CustomJdbcTemplate<User> customJdbcTemplate) {
        this.customJdbcTemplate = customJdbcTemplate;
    }

    @Override
    public User save(User user) {
        String sql = "insert into "
                + UserTable.tableName + " ("
                + UserTable.nickname + ", "
                + UserTable.firstName + ", "
                + UserTable.lastName + ", "
                + UserTable.login + ", "
                + UserTable.email + ", "
                + UserTable.phone + ", "
                + UserTable.password + ", "
                + UserTable.role
                + ") values (?, ?, ?, ?, ?, ?, ?, ?)";
        return customJdbcTemplate.insert(sql,
                UserRowMapper.getRowMapper(),
                user.getNickName(),
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                user.getRole()
        );
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from " + UserTable.tableName;
        try {
            return customJdbcTemplate.findAll(
                    sql,
                    UserRowMapper.getRowMapper()
            );
        } catch (CustomSQLException e) {
            throw new CustomSQLException(e.getMessage());
        }
    }

    @Override
    public User findBy(User user) {
        String sql = "select * from " +
                UserTable.tableName + " where " +
                UserTable.login + " = ? and " +
                UserTable.password + " = ?";
        try {
            return customJdbcTemplate.findBy(
                    sql,
                    UserRowMapper.getRowMapper(),
                    user.getLogin(),
                    user.getPassword()
            );
        } catch (CustomSQLException e) {
            throw new CustomSQLException(e.getMessage());
        } catch (LoginException e) {
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        String sql = "update "
                + UserTable.tableName + " set "
                + UserTable.firstName + " = ?, "
                + UserTable.lastName + " = ?, "
                + UserTable.login + " = ?, "
                + UserTable.email + " = ?, "
                + UserTable.phone + " = ?, "
                + UserTable.password + " = ?"
                + " where "
                + UserTable.id + " = ?";
        try {
            customJdbcTemplate.update(
                    sql,
                    UserRowMapper.getRowMapper(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getLogin(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getPassword(),
                    user.getId()
            );
        } catch (CustomSQLException e) {
            throw new CustomSQLException(e.getMessage());
        }
    }

    @Override
    public void remove(User user) {
        String sql = "delete from "
                + UserTable.tableName + " where "
                + UserTable.id + " = ?";
        try {
            customJdbcTemplate.delete(sql,
                    user.getId());
        } catch (CustomSQLException e) {
            throw new CustomSQLException(e.getMessage());
        }
    }
}
