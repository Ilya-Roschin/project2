package com.java.training.app.userInput.impl;

import com.java.training.app.reader.Reader;
import com.java.training.app.userInput.UserInput;
import com.java.training.app.validator.Validator;
import com.java.training.app.validator.impl.EmailValidator;
import com.java.training.app.validator.impl.NumberValidator;

import java.util.List;

public class InputEmail implements UserInput {

    private static final Validator EMAIL_VALIDATOR = new EmailValidator();
    private static final Reader READER = Reader.getInstance();

    @Override
    public String input() {
        return inputEmail();
    }

    @Override
    public List<String> inputList() {
        return null;
    }


    public String inputEmail() {
        String email = READER.readLine("input new Email: ");
        while (!EMAIL_VALIDATOR.validate(email)) {
            email = READER.readLine("invalid email. Enter email again:");
    }
    return email;
    }
}
