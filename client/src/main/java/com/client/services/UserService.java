package com.client.services;

import com.server.models.UserModel;

import java.util.Vector;

public class UserService {
    private static UserService instance = null;
    private Vector<UserModel> users;

    private UserService() {
        users = new Vector<>(1);
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void setUsers(Vector<UserModel> users) {
        this.users.addAll(users);
    }

    public Vector<UserModel> getUsers() {
        return this.users;
    }

    public UserModel getUser(String login) {
        for (UserModel user : users) {
            if (user.login.equals(login)) {
                return user;
            }
        }
        return null;
    }

    public void setUser(UserModel user) {
        users.add(user);
    }
}
