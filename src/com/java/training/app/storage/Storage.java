package com.java.training.app.storage;

import com.java.training.app.model.User;
import com.java.training.app.reader.Reader;
import com.java.training.app.validator.Validator;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class Storage {

    private static final Validator VALIDATOR = new Validator();
    private static final Reader READER = new Reader();
    private final List<User> users;

    public Storage() {
        this.users = new ArrayList<>();
    }

    public Storage(final List<User> users ) {
        this.users = users;
    }

    public void addUser(final User user) {
        users.add(user);
    }

    public Optional<User> findByFirstName(final String name) {
        return users.stream()
                .filter(user -> name.equals(user.getFirstName()))
                .findFirst();

    }

    public boolean deleteUser(final String firstName) {
        return users.removeIf(user -> firstName.equals(user.getFirstName()));
    }

    public void changeUser(final String name, final User newUser) {
        users.set(users.indexOf(findByFirstName(name)), newUser);
    }

    public List<User> findAllUsers() {
        return Collections.unmodifiableList(users);
    }

    public FileWriter createNewFileTxt(final String name) throws IOException {
        return new FileWriter("resources\\" + name + ".txt");
    }

    public void addUsersToFile(final String fileName) throws IOException {
        final FileWriter file = new FileWriter("resources\\" + fileName + ".txt");
        final StringBuilder text = new StringBuilder();
        for (final User element : users) {
            text.append(element.toString());
        }
        file.write(text.toString());
        file.close();
    }

    public void readFile(final String fileName) throws IOException {
        final FileReader readFile = new FileReader("resources\\" + fileName + ".txt");
        final Scanner scan = new Scanner(readFile);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        readFile.close();
    }

    public void updateFile(final String fileName) throws IOException {
        deleteFile(fileName);
        createNewFileTxt(fileName);
        addUsersToFile(fileName);
    }

    public void deleteFile(final String fileName) throws IOException {
        final File sourceFile = new File("resources\\" + fileName + ".txt");
        sourceFile.delete();
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

    public void updateUser() {
        final String findName = READER.readLine("input username: ");
        final String inputFirstName = READER.readLine("input new first name: ");
        final String inputLastName = READER.readLine("input new last name: ");
        final String inputEmail = READER.readLine("input new Email: ");
        final long id;
        if (findByFirstName(findName).isPresent()) {
            id = findByFirstName(findName).get().getId();
        } else {
            throw new RuntimeException();
        }
        final List<String> numbers = findPhoneNumbers();
        final User newUser = new User(id, inputFirstName, inputLastName, inputEmail, numbers);
        changeUser(findName, newUser);
    }


}
