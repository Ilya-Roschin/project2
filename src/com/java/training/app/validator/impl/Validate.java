package com.java.training.app.validator.impl;

import com.java.training.app.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    //    private static final String REGEX_FOR_PHONE_NUMBERS = "\\d{12}";
    private static final String REGEX_FOR_PHONE_NUMBERS = "\\d{5}\\u0020\\d{7}";
    private static final String REGEX_FOR_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public boolean validateNumber(final String number) {
        pattern = Pattern.compile(REGEX_FOR_PHONE_NUMBERS);
        matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public boolean validateEmail(final String email) {
        pattern = Pattern.compile(REGEX_FOR_EMAIL);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
