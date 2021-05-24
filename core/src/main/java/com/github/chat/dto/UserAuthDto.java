package com.github.chat.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.chat.entity.User;

import java.util.Objects;
import java.util.regex.Pattern;

public class UserAuthDto {

    private String login;

    private String email;

    private String password;

    public UserAuthDto() {
    }

    @JsonCreator
    public UserAuthDto(@JsonProperty("login") String login, @JsonProperty("password") String password) {
        if(Pattern.compile(".*@.*\\..*$").matcher(login).matches()) {
            this.email = login;
            this.password = password;
        } else {
            this.login = login;
            this.password = password;
        }
    }

    public UserAuthDto(User user) {
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthDto that = (UserAuthDto) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password);
    }

    @Override
    public String toString() {
        return "UserAuthDto{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
