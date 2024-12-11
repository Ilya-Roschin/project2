package com.java.training.app.service;

import com.java.training.app.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserService {

    private static final String FILE_PATH = "resources\\storage.txt";
    private static UserService instance;
    private static final FileService FILE_SERVICE = FileService.getInstance();

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public List<User> findAllUsers() {
        final List<String> text = FILE_SERVICE.fileToStringList(FILE_PATH);
        final List<User> users = new ArrayList<>();
        for (final String element : text) {
            final String[] data = element.split(";");
            final List<String> phoneNumbers = Arrays.asList(data[4].split(","));
            final User user = new User(data[0], data[1], data[2], data[3], phoneNumbers);
            users.add(user);
        }
        return users;
    }

    public Optional<User> findByFirstName(final String name) {
        return findAllUsers().stream()
                .filter(user -> name.equals(user.getFirstName()))
                .findFirst();
    }

    public void addUserToFile(final User user) throws IOException {
        List<User> users = findAllUsers();
        users.add(user);
        addUsersToFile(users);
    }

    public void addUsersToFile(final List<User> users) throws IOException {
        try (final FileWriter file = new FileWriter(FILE_PATH)) {
            for (final User element : users) {
                file.write(element.toString());
            }
        }
    }

    public boolean deleteUser(final String firstName) throws IOException {
        boolean deleted = false;
        final List<User> users = findAllUsers();
        if (users.removeIf(user -> firstName.equals(user.getFirstName()))) {
            FILE_SERVICE.deleteFile(FILE_PATH);
            FILE_SERVICE.createNewFileTxt();
            addUsersToFile(users);
            deleted = true;
        }
        return deleted;
    }

}
