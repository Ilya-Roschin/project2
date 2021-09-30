package com.java.training.app.menu;


import com.java.training.app.model.User;
import com.java.training.app.reader.Reader;
import com.java.training.app.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    private static final Storage STORAGE = new Storage();
    private static final Reader READER = new Reader();
    private static final String REGEX_FOR_PHONE_NUMBERS;
    private static final String REGEX_FOR_EMAIL;
    private static Pattern pattern;
    private static Matcher matcher;


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

    static {
        REGEX_FOR_PHONE_NUMBERS = "\\d{5}\\u0020\\d{7}";
        REGEX_FOR_EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@ [A-Za-z0-9]+" +
                "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
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
                updateUser();
                break;
            case 6:
                createNewFileTxt();
                break;
            case 7:
                addTextToFile();
                break;
            case 8:
                readFile();
                break;
            case 9:
                updateFile();
                break;
            case 10:
                deleteFile();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("no such operations");
        }
    }

    private void addUser() {
        final String firstName = READER.readLine("input first name: ");
        final String lastName = READER.readLine("input Last name: ");
        String email = READER.readLine("input new Email: ");
        while (!validateEmail(email)) {
            email = READER.readLine("invalid email. Try again:");
        }
        final List<String> numbers = findPhoneNumbers();
        final User user = new User(firstName, lastName, email, numbers);
        STORAGE.addUser(user);
    }

    private List<String> findPhoneNumbers() {
        final int i = 0;
        final List<String> numbers = new ArrayList<>();
        int amountOfNumbers = enterAmountOfPhoneNumbers();

        while (amountOfNumbers > 0) {
            String number = READER.readLine("Enter phone number: ");
            while (!validateNumber(number)) {
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

    private boolean validateNumber(final String number) {
        pattern = Pattern.compile(REGEX_FOR_PHONE_NUMBERS);
        matcher = pattern.matcher(number);
        return matcher.matches();
    }

    private boolean validateEmail(final String email) {
        pattern = Pattern.compile(REGEX_FOR_EMAIL);
        matcher = pattern.matcher(email);
        return matcher.matches();
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

    private void updateUser() {
        final String findName = READER.readLine("input user name: ");
        final String inputFirstName = READER.readLine("input new first name: ");
        final String inputLastName = READER.readLine("input new last name: ");
        final String inputEmail = READER.readLine("input new Email: ");
        final long id = STORAGE.findByFirstName(findName).getId();
        final List<String> numbers = findPhoneNumbers();
        final User newUser = new User(id, inputFirstName, inputLastName, inputEmail, numbers);
        STORAGE.changeUser(findName, newUser);
    }

    private void createNewFileTxt() throws IOException {
        final String fileName = READER.readLine("Enter file name: ");
        STORAGE.createNewFileTxt(fileName);
    }

    private void addTextToFile() throws IOException {
        final String fileName = READER.readLine("Enter file name: ");
        STORAGE.addUsersToFile(fileName);
    }

    private void readFile() throws IOException {
        final String fileName = READER.readLine("Enter file name: ");
        STORAGE.readFile(fileName);
    }

    private void updateFile() throws IOException {
        final String fileName = READER.readLine("Enter file name: ");
        STORAGE.updateFile(fileName);
    }

    private void deleteFile() throws IOException {
        final String fileName = READER.readLine("Enter file name: ");
        STORAGE.deleteFile(fileName);
    }
}

