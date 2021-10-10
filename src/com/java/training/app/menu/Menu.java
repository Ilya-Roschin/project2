package com.java.training.app.menu;


import com.java.training.app.model.User;
import com.java.training.app.reader.Reader;
import com.java.training.app.storage.FileService;
import com.java.training.app.storage.PhoneNumberService;
import com.java.training.app.validator.Validator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private static final PhoneNumberService STORAGE = new PhoneNumberService();
    private static final Reader READER = new Reader();
    private static final Validator VALIDATOR = new Validator();
    private static final FileService FILE_SERVICE_1 = new FileService();

    public static void run() {
        final Menu menu = new Menu();
        try {
            while (true) {

                menu.printMenu();
                final int choice = menu.readMenu();
                menu.makeChoice(choice);

            }
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. add new user");
        System.out.println("2. find user by user name");
        System.out.println("3. delete user by user name");
        System.out.println("4. find all users");
        System.out.println("0. exit");
    }

    public int readMenu() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void makeChoice(final int choice) throws IOException {
        switch (choice) {
            case 1:
                addUser();
                break;
            case 2:
                findUser();
                break;
            case 3:
                deleteUser();
                break;
            case 4:
                findAllUsers();
                break;
            case 5:
                FILE_SERVICE_1.readFile();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("no such operations");
        }
    }

    private void addUser() throws IOException {
        final String firstName = READER.readLine("input first name: ");
        final String lastName = READER.readLine("input Last name: ");
        String email = READER.readLine("input new Email: ");
        while (!VALIDATOR.validateEmail(email)) {
            email = READER.readLine("invalid email. Try again:");
        }
        final List<String> numbers = STORAGE.findPhoneNumbers();
        final User user = new User(firstName, lastName, email, numbers);
        FILE_SERVICE_1.addUserToFile(user);
    }

    private void findUser() throws IOException {
        final String firstName = READER.readLine("Enter user name: ");
        final Optional<User> foundUser = FILE_SERVICE_1.findByFirstName(firstName);
        foundUser.ifPresent(System.out::println);
    }

    private void deleteUser() throws IOException {
        String message = "User not found";
        final String name = READER.readLine("Enter user name: ");
        if (FILE_SERVICE_1.deleteUser(name)) {
            message = "User deleted";
        }
        System.out.println(message);
    }

    private void findAllUsers() throws IOException {
        final List<User> foundedUsers = FILE_SERVICE_1.findAllUsers();
        if (foundedUsers.size() == 0) {
            System.out.println("No users!");
        } else {
            foundedUsers.forEach(System.out::println);
        }
    }
}

