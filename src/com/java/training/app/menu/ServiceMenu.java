package com.java.training.app.menu;

import com.java.training.app.model.User;
import com.java.training.app.reader.Reader;
import com.java.training.app.service.PhoneNumberService;
import com.java.training.app.service.UserService;
import com.java.training.app.userInput.InputList;
import com.java.training.app.userInput.InputString;
import com.java.training.app.userInput.impl.InputEmail;
import com.java.training.app.userInput.impl.InputPhoneNumbers;
import com.java.training.app.userInput.impl.InputRole;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServiceMenu {

    private static final Reader READER = Reader.getInstance();
    private static final UserService USER_SERVICE = UserService.getInstance();
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
        final InputString roleInput = new InputRole();
        final String role = roleInput.inputString();
        final String firstName = READER.readLine("input first name: ");
        final String lastName = READER.readLine("input Last name: ");
        final InputString emailInput = new InputEmail();
        final String email = emailInput.inputString();
        final InputList numbersInput = new InputPhoneNumbers();
        final List<String> numbers = numbersInput.inputList();
        final User user = new User(role, firstName, lastName, email, numbers);
        USER_SERVICE.addUserToFile(user);
    }

    public void findUser() {
        final String firstName = READER.readLine("Enter user name: ");
        final Optional<User> foundUser = USER_SERVICE.findByFirstName(firstName);
        foundUser.ifPresent(System.out::println);
    }

    public void deleteUser() throws IOException {
        String message = "User not found";
        final String name = READER.readLine("Enter user name: ");
        if (USER_SERVICE.deleteUser(name)) {
            message = "User deleted";
        }
        System.out.println(message);
    }

    public void findAllUsers() {
        final List<User> foundUsers = USER_SERVICE.findAllUsers();
        foundUsers.stream().collect(Collectors.groupingBy(User::getRole));
        if (!foundUsers.isEmpty()) {
            foundUsers.forEach(System.out::println);
        } else {
            System.out.println("No users!");
        }
    }
}
