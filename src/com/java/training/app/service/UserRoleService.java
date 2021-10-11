package com.java.training.app.service;

import com.java.training.app.model.UserRole;

public class UserRoleService {

    public boolean isRole(final String userRole) {
        for (final UserRole role : UserRole.values()) {
            if (role.name().equals(userRole)) {
                return true;
            }
        }
        return false;
    }
}
