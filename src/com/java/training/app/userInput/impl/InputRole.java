package com.java.training.app.userInput.impl;

import com.java.training.app.reader.Reader;
import com.java.training.app.service.UserRoleService;
import com.java.training.app.userInput.InputList;
import com.java.training.app.userInput.InputString;

import java.util.List;

public class InputRole implements InputString {

    private static final Reader READER = Reader.getInstance();
    private static final UserRoleService USER_ROLE_SERVICE = UserRoleService.getInstance();

    @Override
    public String inputString() {
        return inputRole();
    }

    private String inputRole() {
        USER_ROLE_SERVICE.printRoleMenu();
        return USER_ROLE_SERVICE.choseRole();
    }
}
