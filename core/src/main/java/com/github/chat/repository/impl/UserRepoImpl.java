package com.github.chat.repository.impl;

import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.micro.orm.exceptions.CustomSQLException;
import com.github.chat.exceptions.LoginException;
import com.github.chat.exceptions.PasswordException;
import com.github.chat.repository.UsersRepository;
import com.github.chat.repository.userTable.UserRowMapper;
import com.github.chat.repository.userTable.UserTable;
import com.github.micro.orm.CustomJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserRepoImpl implements UsersRepository {

    private CustomJdbcTemplate<User> customJdbcTemplate;

    private final DataSource dataSource;

    public UserRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.customJdbcTemplate = new CustomJdbcTemplate<>(this.dataSource);
    }


    @Override
    public User save(UserRegDto userRegDto) {
        String sql = "insert into "
                + UserTable.tableName + " ("
                + UserTable.firstName + ", "
                + UserTable.lastName + ", "
                + UserTable.login + ", "
                + UserTable.email + ", "
                + UserTable.phone + ", "
                + UserTable.password
                + ") values (?, ?, ?, ?, ?, ?)";
        return customJdbcTemplate.insert(sql,
                UserRowMapper.getRowMapper(),
                userRegDto.getFirstName(),
                userRegDto.getLastName(),
                userRegDto.getLogin(),
                userRegDto.getEmail(),
                userRegDto.getPhone(),
                userRegDto.getPassword()
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
    public User findAuth(UserAuthDto userAuthDto) {
        String sql = "select * from "
                + UserTable.tableName + " where "
                + UserTable.login + " = ?";
        try {
            User result = customJdbcTemplate.findBy(
                    sql,
                    UserRowMapper.getRowMapper(),
                    userAuthDto.getLogin()
            );
            if(userAuthDto.getPassword().equals(result.getPassword())){
                return result;
            } else {
                throw new PasswordException("Wrong password for user " + userAuthDto.getLogin());
            }
        } catch (CustomSQLException e) {
            throw new CustomSQLException(e.getMessage());
        } catch (LoginException e){
            throw new LoginException("Wrong login: " + userAuthDto.getLogin());
        }
    }

    @Override
    public User findReg(UserRegDto userRegDto) {
        String sql = "select * from "
                + UserTable.tableName + " where "
                + UserTable.login + " = ? and "
                + UserTable.email + " = ? and "
                + UserTable.phone + " = ?";
        try {
            return customJdbcTemplate.findBy(
                    sql,
                    UserRowMapper.getRowMapper(),
                    userRegDto.getLogin(),
                    userRegDto.getEmail(),
                    userRegDto.getPhone()
            );
        } catch (CustomSQLException e) {
            throw new CustomSQLException(e.getMessage());
        } catch (LoginException e){
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public User update(User user) {
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
            return customJdbcTemplate.update(sql,
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
