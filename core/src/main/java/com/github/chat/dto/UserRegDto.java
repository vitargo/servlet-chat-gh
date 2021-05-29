package com.github.chat.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserRegDto {

    private long id;

    private String nickName;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    private String email;

    private String phone;

    private boolean verification;

    private String companyName;

    private String avatar;

    public UserRegDto() {}


    public UserRegDto(String nickName) {
        this.nickName = nickName;
    }

    @JsonCreator
    public UserRegDto(@JsonProperty("id")long id,
                      @JsonProperty("nickName")String nickName,
                      @JsonProperty("firstName")String firstName,
                      @JsonProperty("lastName")String lastName,
                      @JsonProperty("login")String login,
                      @JsonProperty("password")String password,
                      @JsonProperty("email")String email,
                      @JsonProperty("phone")String phone,
                      @JsonProperty("companyName")String companyName,
                      @JsonProperty("avatar")String avatar) {
        this.id = id;
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.avatar = avatar;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegDto that = (UserRegDto) o;
        return id == that.id &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, firstName, lastName, login, password, email, phone, companyName, avatar);
    }



    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    @Override
    public String toString() {
        return "UserRegDto{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", verification=" + verification +
                ", companyName='" + companyName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
