package com.java.training.app.storage;

import com.java.training.app.model.User;
import com.java.training.app.reader.Reader;
import com.java.training.app.validator.Validator;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberService {

    private static final Validator VALIDATOR = new Validator();
    private static final Reader READER = new Reader();
    private final List<User> users;

    public PhoneNumberService() {
        this.users = new ArrayList<>();
    }

    public List<String> findPhoneNumbers() {
        final List<String> numbers = new ArrayList<>();
        int amountOfNumbers = enterAmountOfPhoneNumbers();

        while (amountOfNumbers > 0) {
            String number = READER.readLine("Enter phone number: ");
            while (!VALIDATOR.validateNumber(number)) {
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
