package com.java.training.app.service;

public class UserRoleService {

    private static UserRoleService instance;

    private UserRoleService() {
    }

    public static UserRoleService getInstance() {
        if (instance == null) {
            instance = new UserRoleService();
        }
        return instance;
    }

}
