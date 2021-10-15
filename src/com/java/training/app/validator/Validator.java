package com.java.training.app.validator;

@FunctionalInterface
public interface Validator {

    boolean validate(final String input);
}
