package com.java.training.app.model;

public enum UserRole {
    USER,
    ADMINISTRATOR;

    public static boolean isRole(final String userRole) {
        for (final UserRole role : UserRole.values()) {
            if (role.name().equals(userRole)) {
                return true;
            }
        }
        return false;
        // TODO: 14.10.2021 переписать или сделать через стримы
    }
}
