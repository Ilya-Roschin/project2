package com.java.training.app.menu;

import com.java.training.app.model.User;
import com.java.training.app.reader.Reader;
import com.java.training.app.service.FileService;
import com.java.training.app.service.PhoneNumberService;
import com.java.training.app.service.UserRoleService;
import com.java.training.app.validator.impl.ValidatorImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ApplicationMenu {

    private static final PhoneNumberService PHONE_NUMBER_SERVICE = PhoneNumberService.getInstance();
    private static final Reader READER = Reader.getInstance();
    private static final FileService FILE_SERVICE = FileService.getInstance();
    private static final UserRoleService USER_ROLE_SERVICE = UserRoleService.getInstance();
    private static final ValidatorImpl VALIDATOR_IMPL = new ValidatorImpl();
    private static ApplicationMenu instance;

    private ApplicationMenu() {
    }

    public static ApplicationMenu getInstance() {
        if (instance == null) {
            instance = new ApplicationMenu();
        }
        return instance;
    }

    public void addUser() throws IOException {
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
        final List<User> foundedUsers = FILE_SERVICE.findAllUsers();
        if (foundedUsers.isEmpty()) {
            System.out.println("No users!");
        } else {
            foundedUsers.forEach(System.out::println);
        }
    }
}
