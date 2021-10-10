package com.java.training.app.storage;

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

    private static final String FILE_PATH = "C:\\projects\\project2\\src\\com\\java\\training\\app\\resource\\storage.txt";

    public List<User> findAllUsers() throws IOException {
        final List<String> text = fileToStringList(FILE_PATH);
        final List<User> users = new ArrayList<>();
        for (final String element : text) {
            final String[] data = element.split(" ");
            final List<String> phoneNumbers = Arrays.asList(data[3].split(","));
            final User user = new User(data[0], data[1], data[2], phoneNumbers);
            users.add(user);
        }
        return users;
    }

    public Optional<User> findByFirstName(final String name) throws IOException {
        return findAllUsers().stream()
                .filter(user -> name.equals(user.getFirstName()))
                .findFirst();
    }

    public void addUserToFile(final User user) throws IOException {
        final FileWriter file = new FileWriter(FILE_PATH);
        file.write(user.toString());
        file.close();
    }

    public void addUsersToFile(final List<User> users) throws IOException {
        final FileWriter file = new FileWriter(FILE_PATH);
        for (final User element : users) {
            file.write(element.toString());
        }
        file.close();
    }

    public FileWriter createNewFileTxt() throws IOException {
        return new FileWriter(FILE_PATH);
    }

    public void deleteFile(final String fileName) throws IOException {
        final File sourceFile = new File(FILE_PATH);
        sourceFile.delete();
    }

    public boolean deleteUser(final String firstName) throws IOException {
        List<User> users = findAllUsers();
        if (users.removeIf(user -> firstName.equals(user.getFirstName()))) {
            deleteFile(FILE_PATH);
            createNewFileTxt();
            addUsersToFile(users);
            return true;
        } else {
            return false;
        }
    }

    public void readFile() throws IOException {
        fileToStringList(FILE_PATH).forEach(System.out::println);
    }

    private List<String> fileToStringList(final String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        }
    }
}
