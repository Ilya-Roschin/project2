package com.java.training.app.service;

import com.java.training.app.model.UserRole;

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

    public boolean isRole(final String userRole) {
        for (final UserRole role : UserRole.values()) {
            if (role.name().equals(userRole)) {
                return true;
            }
        }
        return false;
    }
}
