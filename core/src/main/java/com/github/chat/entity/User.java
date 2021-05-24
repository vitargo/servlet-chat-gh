package com.github.chat.entity;

import com.github.chat.utils.PasswordProvider;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sc_users")
public class User implements Serializable {

    private static final long serialVersionUID = -5210081818555834792L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private long id;

    @Column (name = "nickname")
    private String nickName;

    @Column (name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name = "email")
    private String email;

    @Column (name = "login")
    private String login;

    @Column (name = "password")
    private String password;

    @Column (name = "phone")
    private String phone;

    @Column (name = "role")
    private int role;

    @Column (name = "verification")
    private boolean verification;

    @Column (name = "company_name")
    private String companyName;

    @Column (name = "avatar")
    private String avatar;

    @Column (name = "salt")
    private String salt;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String nickName, String firstName,
                String lastName, String email,
                String login, String password,
                String phone, String companyName, String avatar) {
        String[] hash = new String[2];
        if(password != null) {
            hash = PasswordProvider.encodePassReg(password);
        }
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = hash[1];
        this.phone = phone;
        this.role = 2;
        this.verification = Boolean.FALSE;
        this.companyName = companyName;
        this.salt = hash[0];
        this.avatar = avatar;
    }

    public User(long id, String nickName, String firstName,
                String lastName, String email, String login,
                String password, String phone, boolean verification,
                String companyName, String avatar) {
        this.id = id;
        String[] hash = new String[2];
        if(password != null) {
            hash = PasswordProvider.encodePassReg(password);
        }
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = hash[1];
        this.phone = phone;
        this.role = 2;
        this.verification = verification;
        this.companyName = companyName;
        this.salt = hash[0];
        this.avatar = avatar;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isVerification() {
        return verification;
    }

    public void setVerification(boolean verification) {
        this.verification = verification;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                role == user.role &&
                verification == user.verification &&
                Objects.equals(nickName, user.nickName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(companyName, user.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, firstName, lastName, email, login, password, phone, role, verification, companyName);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", verification=" + verification +
                ", companyName='" + companyName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
