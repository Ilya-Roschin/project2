package com.java.training.app.menu;

import com.java.training.app.model.User;
import com.java.training.app.model.UserRole;
import com.java.training.app.reader.Reader;
import com.java.training.app.service.FileService;
import com.java.training.app.service.PhoneNumberService;
import com.java.training.app.service.UserRoleService;
import com.java.training.app.validator.impl.ValidatorImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private static final PhoneNumberService PHONE_NUMBER_SERVICE = new PhoneNumberService();
    private static final Reader READER = new Reader();
    private static final ValidatorImpl VALIDATOR_IMPL = new ValidatorImpl();
    private static final FileService FILE_SERVICE = new FileService();
    private static final UserRoleService USER_ROLE_SERVICE = new UserRoleService();

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
                FILE_SERVICE.readFile();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("no such operations");
        }
    }

    private void addUser() throws IOException {

        String role = READER.readLine("input User role: ");
        while (!USER_ROLE_SERVICE.isRole(role)) {
            role = READER.readLine("invalid role. Enter role again:");
        }
        final String firstName = READER.readLine("input first name: ");
        final String lastName = READER.readLine("input Last name: ");
        String email = READER.readLine("input new Email: ");
        while (!VALIDATOR_IMPL.validateEmail(email)) {
            email = READER.readLine("invalid email. Enter email again:");
        }
        final List<String> numbers = PHONE_NUMBER_SERVICE.findPhoneNumbers();
        final User user = new User(role, firstName, lastName, email, numbers);
        FILE_SERVICE.addUserToFile(user);
    }

    private void findUser() {
        final String firstName = READER.readLine("Enter user name: ");
        final Optional<User> foundUser = FILE_SERVICE.findByFirstName(firstName);
        foundUser.ifPresent(System.out::println);
    }

    private void deleteUser() throws IOException {
        String message = "User not found";
        final String name = READER.readLine("Enter user name: ");
        if (FILE_SERVICE.deleteUser(name)) {
            message = "User deleted";
        }
        System.out.println(message);
    }

    private void findAllUsers() throws IOException {
        final List<User> foundedUsers = FILE_SERVICE.findAllUsers();
        if (foundedUsers.isEmpty()) {
            System.out.println("No users!");
        } else {
            foundedUsers.forEach(System.out::println);
        }
    }
}

