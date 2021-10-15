package com.java.training.app.validator.impl;

import com.java.training.app.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {

    private static final String REGEX_FOR_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public boolean validate(final String email) {
        final Pattern pattern = Pattern.compile(REGEX_FOR_EMAIL);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
