package com.java.training.app.userInput.impl;

import com.java.training.app.model.UserRole;
import com.java.training.app.reader.Reader;
import com.java.training.app.userInput.UserInput;


import java.util.List;

public class InputRole implements UserInput {

    private static final Reader READER = Reader.getInstance();

    @Override
    public String input() {
        return inputRole();
    }

    @Override
    public List<String> inputList() {
        return null;
    }

    private String inputRole() {
        String role = READER.readLine("input User role: ");
        while (!UserRole.isRole(role)) {
            role = READER.readLine("invalid role. Enter role again:");
    }
        return role;
    }
}
