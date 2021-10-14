package com.java.training.app.menu;

import com.java.training.app.model.User;
import com.java.training.app.model.UserRole;
import com.java.training.app.reader.Reader;
import com.java.training.app.service.FileService;
import com.java.training.app.service.PhoneNumberService;
import com.java.training.app.validator.Validator;
import com.java.training.app.validator.impl.EmailValidator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ServiceMenu {

    private static final PhoneNumberService PHONE_NUMBER_SERVICE = PhoneNumberService.getInstance();
    private static final Reader READER = Reader.getInstance();
    private static final FileService FILE_SERVICE = FileService.getInstance();
    private static final Validator VALIDATOR_EMAIL = new EmailValidator();
    private static ServiceMenu instance;

    private ServiceMenu() {
    }

    public static ServiceMenu getInstance() {
        if (instance == null) {
            instance = new ServiceMenu();
        }
        return instance;
    }

    public void addUser() throws IOException {

        String role = READER.readLine("input User role: ");
        while (!UserRole.isRole(role)) {
            role = READER.readLine("invalid role. Enter role again:");
            // TODO: 14.10.2021 предоставить список ролей или выбор ролей
        }
        final String firstName = READER.readLine("input first name: ");
        final String lastName = READER.readLine("input Last name: ");
        String email = READER.readLine("input new Email: ");
        while (!VALIDATOR_EMAIL.validate(email)) {
            email = READER.readLine("invalid email. Enter email again:");
        }
        final List<String> numbers = PHONE_NUMBER_SERVICE.inputPhoneNumbers();
        final User user = new User(role, firstName, lastName, email, numbers);
        FILE_SERVICE.addUserToFile(user);
        // TODO: 14.10.2021 сделать как на 45 строке+ использоать паттерн СТРАТЕГИЯ!!!
    }

    public void findUser() {
        final String firstName = READER.readLine("Enter user name: ");
        final Optional<User> foundUser = FILE_SERVICE.findByFirstName(firstName);
        foundUser.ifPresent(System.out::println);
    }

    public void deleteUser() throws IOException {
        String message = "User not found";
        final String name = READER.readLine("Enter user name: ");
        if (FILE_SERVICE.deleteUser(name)) {
            message = "User deleted";
        }
        System.out.println(message);
    }

    public void findAllUsers() {
        final List<User> foundUsers = FILE_SERVICE.findAllUsers();
        if (!foundUsers.isEmpty()) {
            foundUsers.forEach(System.out::println);
        } else {
            System.out.println("No users!");
        }
    }
}
