package com.java.training.app.service;

import com.java.training.app.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {

    private static final String FILE_PATH = "resources.storage.txt";

    public List<User> findAllUsers() {
        final List<String> text = fileToStringList(FILE_PATH);
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
        try (final FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(user.toString());
        }
    }

    public void addUsersToFile(final List<User> users) throws IOException {
        try (final FileWriter file = new FileWriter(FILE_PATH)) {
            for (final User element : users) {
                file.write(element.toString());
            }
        }
    }

    public FileWriter createNewFileTxt() throws IOException {
        return new FileWriter(FILE_PATH);
    }

    public void deleteFile(final String fileName) {
        final File sourceFile = new File(fileName);
        sourceFile.delete();
    }

    public boolean deleteUser(final String firstName) throws IOException {
        final List<User> users = findAllUsers();
        if (users.removeIf(user -> firstName.equals(user.getFirstName()))) {
            deleteFile(FILE_PATH);
            createNewFileTxt();
            addUsersToFile(users);
            return true;
        } else {
            return false;
        }
    }

    public void readFile() {
        fileToStringList(FILE_PATH).forEach(System.out::println);
    }

    private List<String> fileToStringList(final String fileName) {
        try (final Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO: 10.10.2021 исправить Exception и return
        return null;
    }
}