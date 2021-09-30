package com.java.training.app.menu;

import com.java.training.app.file.File;
import com.java.training.app.model.User;
import com.java.training.app.reader.Reader;
import com.java.training.app.storage.Storage;
import com.java.training.app.validator.Validator;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final Storage STORAGE = new Storage();
    private static final Reader READER = new Reader();
    private static final Validator VALIDATOR = new Validator();
    private static final File FILE = new File();

    public static void run() {
        final Menu menu = new Menu();
        while (true) {
            try {
                menu.printMenu();
                final int choice = menu.readMenu();
                menu.makeChoice(choice);
            } catch (final Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. add new user");
        System.out.println("2. find user by user name");
        System.out.println("3. delete user by user name");
        System.out.println("4. find all users");
        System.out.println("5. remove user by user name");
        System.out.println("6. create new file");
        System.out.println("7. add users to file");
        System.out.println("8. read file");
        System.out.println("9. update file");
        System.out.println("10. delete file");
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
                STORAGE.updateUser();
                break;
            case 6:
                FILE.createNewFileTxt();
                break;
            case 7:
                FILE.addTextToFile();
                break;
            case 8:
                FILE.readFile();
                break;
            case 9:
                FILE.updateFile();
                break;
            case 10:
                FILE.deleteFile();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("no such operations");
        }
    }

    private void addUser() {
        final String firstName = READER.readLine("input first name: ");
        final String lastName = READER.readLine("input Last name: ");
        String email = READER.readLine("input new Email: ");
        while (!VALIDATOR.validateEmail(email)) {
            email = READER.readLine("invalid email. Try again:");
        }
        final List<String> numbers = STORAGE.findPhoneNumbers();
        final User user = new User(firstName, lastName, email, numbers);
        STORAGE.addUser(user);
    }

    private void findUser() {
        final String firstName = READER.readLine("Enter user name: ");
        final User foundUser = STORAGE.findByFirstName(firstName);
        System.out.println(foundUser);
    }

    private void deleteUser() {
        String message = "User not found";
        final String name = READER.readLine("Enter user name: ");
        if (STORAGE.deleteUser(name)) {
            message = "User deleted";
        }
        System.out.println(message);
    }

    private void findAllUsers() {
        final List<User> foundedUsers = STORAGE.findAllUsers();
        if (foundedUsers.size() == 0) {
            System.out.println("No users!");
        } else {
            foundedUsers.forEach(System.out::println);
        }
    }
}

