package com.example.flightclient.service;

import com.example.flightclient.entity.UserEntity;

public class UserContext {
    boolean isAdmin;
    private static UserContext instance;
    private UserEntity currentUser;

    private UserContext() {
    }

    public static UserContext getInstance() {
        if (instance == null) {
            instance = new UserContext();
        }
        return instance;
    }

    public UserEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isAdmin() {

        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

