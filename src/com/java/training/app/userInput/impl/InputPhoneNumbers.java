package com.java.training.app.userInput.impl;

import com.java.training.app.reader.Reader;
import com.java.training.app.userInput.UserInput;
import com.java.training.app.validator.Validator;
import com.java.training.app.validator.impl.NumberValidator;

import java.util.ArrayList;
import java.util.List;

public class InputPhoneNumbers implements UserInput {

    private static final Validator NUMBER_VALIDATOR = new NumberValidator();
    private static final Reader READER = Reader.getInstance();

    @Override
    public String input() {
        return null;
    }

    @Override
    public List<String> inputList() {
        return inputPhoneNumbers();
    }

    public List<String> inputPhoneNumbers() {
        final List<String> numbers = new ArrayList<>();
        int amountOfNumbers = enterAmountOfPhoneNumbers();
        while (amountOfNumbers > 0) {
            String number = READER.readLine("Enter phone number: ");
            while (!NUMBER_VALIDATOR .validate(number)) {
                number = READER.readLine("invalid number. Try again:");
            }
            numbers.add(number);
            amountOfNumbers--;
        }
        return numbers;
    }

    private int enterAmountOfPhoneNumbers() {
        while (true) {
            final int amountOfNumbers = READER.readInt("how many phone numbers do you want to enter: ");
            if (amountOfNumbers <= 0 || amountOfNumbers > 3) {
                System.out.println("invalid number. Try again. ");
            } else {
                return amountOfNumbers;
            }
        }
    }
}
