package com.github.chat.repository.impl;

import com.github.chat.controllers.UsersController;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.User;
import com.github.chat.exceptions.LoginException;
import com.github.chat.repository.UsersRepository;
import com.github.chat.repository.userTable.UserRowMapper;
import com.github.chat.repository.userTable.UserTable;
import com.github.micro.orm.CustomJdbcTemplate;
import com.github.micro.orm.exceptions.CustomSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

public class UserRepoImpl implements UsersRepository {

    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    private CustomJdbcTemplate<User> customJdbcTemplate;

    private DataSource dataSource;

    public UserRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.customJdbcTemplate = new CustomJdbcTemplate<>(this.dataSource);
    }

    @Override
    public User save(UserRegDto userRegDto) {
        String sql = "insert into "
                + UserTable.tableName + " ("
                + UserTable.nickname + ", "
                + UserTable.firstName + ", "
                + UserTable.lastName + ", "
                + UserTable.login + ", "
                + UserTable.email + ", "
                + UserTable.phone + ", "
                + UserTable.password + ", "
                +UserTable.role
                + ") values (?, ?, ?, ?, ?, ?, ?, ?)";
        return customJdbcTemplate.insert(sql,
                UserRowMapper.getRowMapper(),
                userRegDto.getNickname(),
                userRegDto.getFirstName(),
                userRegDto.getLastName(),
                userRegDto.getLogin(),
                userRegDto.getEmail(),
                userRegDto.getPhone(),
                userRegDto.getPassword(),
                userRegDto.getRole()
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

            if(Objects.isNull(result) || !userAuthDto.getPassword().equals(result.getPassword())){
                log.error("Wrong password for user " + userAuthDto.getLogin());
                return null;
            } else {
                return result;
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
