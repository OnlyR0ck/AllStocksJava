package com.server.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    public int userID;
    public String nickname;
    public int role;
    public String login;
    public String password;

    public UserModel setUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public UserModel setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserModel setRole(int role) {
        this.role = role;
        return this;
    }

    public UserModel setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }
}

