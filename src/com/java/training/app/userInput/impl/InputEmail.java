package com.java.training.app.userInput.impl;

import com.java.training.app.reader.Reader;
import com.java.training.app.userInput.InputString;
import com.java.training.app.validator.Validator;
import com.java.training.app.validator.impl.EmailValidator;

public class InputEmail implements InputString {

    private static final Validator EMAIL_VALIDATOR = new EmailValidator();
    private static final Reader READER = Reader.getInstance();

    @Override
    public String inputString() {
        return inputEmail();
    }

    public String inputEmail() {
        String email = READER.readLine("input new Email: ");
        while (!EMAIL_VALIDATOR.validate(email)) {
            email = READER.readLine("invalid email. Enter email again:");
    }
    return email;
    }



}
