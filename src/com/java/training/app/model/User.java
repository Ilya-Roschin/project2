package com.java.training.app.model;

import java.util.List;
import java.util.UUID;

public class User {

    private final List<String> numbers;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final long id;

    public User(String firstName, String lastName, String email, List<String> numbers) {
        this.id = UUID.randomUUID().getMostSignificantBits();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numbers = numbers;
    }

    public User(long id, String firstName, String lastName, String email, List<String> numbers) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "User name: " + firstName + "\n" +
                "Email: " + email + "\n" +

                "Phone number: " + numbers.toString() + "\n" +
                "---------------" + "\n";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getMobileNumber() {
        return numbers;
    }

    public long getId() {
        return id;
    }

}
