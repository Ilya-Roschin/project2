package com.java.training.app.userInput.impl;

import com.java.training.app.model.UserRole;
import com.java.training.app.reader.Reader;
import com.java.training.app.service.UserRoleService;
import com.java.training.app.userInput.UserInput;

import java.util.List;

public class InputRole implements UserInput {

    private static final Reader READER = Reader.getInstance();
    private static final UserRoleService USER_ROLE_SERVICE = UserRoleService.getInstance();

    @Override
    public String inputString() {
        return inputRole();
    }

    @Override
    public List<String> inputList() {
        return null;
    }

    private String inputRole() {
        USER_ROLE_SERVICE.printRoleMenu();
        String role = USER_ROLE_SERVICE.choseRole();
        return role;
    }
}
