package com.java.training.app.service;

import com.java.training.app.reader.Reader;
import com.java.training.app.validator.impl.Validate;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberService {

    private static final Validate VALIDATOR_IMPL = new Validate();
    private static final Reader READER = new Reader();

//    private static PhoneNumber instance;
//    private PhoneNumber(){}
//    public static LazyInitializedSingleton getInstance(){ // #3
//        if(instance == null){		//если объект еще не создан
//            instance = new LazyInitializedSingleton();	//создать новый объект
//        }
//        return instance;		// вернуть ранее созданный объект
//    }

    public List<String> findPhoneNumbers() {
        final List<String> numbers = new ArrayList<>();
        int amountOfNumbers = enterAmountOfPhoneNumbers();

        while (amountOfNumbers > 0) {
            String number = READER.readLine("Enter phone number: ");
            while (!VALIDATOR_IMPL.validateNumber(number)) {
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
