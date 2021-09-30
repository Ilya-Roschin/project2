package com.java.training.app.storage;

import com.java.training.app.model.User;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final List<User> users = new ArrayList<>();

    public void addUser(final User user) {
        users.add(user);
    }

    public User findByFirstName(final String name) {
        return users.stream()
                .filter(user -> name.equals(user.getFirstName()))
                .findFirst()
                .get();
    }

    public boolean deleteUser(final String firstName) {
        return users.removeIf(user -> firstName.equals(user.getFirstName()));
    }

    public void changeUser(final String name, final User newUser) {
        users.set(users.indexOf(findByFirstName(name)), newUser);
    }

    public List<User> findAllUsers() {
        return Collections.unmodifiableList(users);
    }

    public FileWriter createNewFileTxt(final String name) throws IOException {
        return new FileWriter("resources\\" + name + ".txt");
    }

    public void addUsersToFile(final String fileName) throws IOException {
        final FileWriter file = new FileWriter("resources\\" + fileName + ".txt");
        final StringBuilder text = new StringBuilder();
        for (final User element : users) {
            text.append(element.toString());
        }
        file.write(text.toString());
        file.close();
    }

    public void readFile(final String fileName) throws IOException {
        final FileReader readFile = new FileReader("resources\\" + fileName + ".txt");
        final Scanner scan = new Scanner(readFile);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        readFile.close();
    }

    public void updateFile(final String fileName) throws IOException {
        deleteFile(fileName);
        createNewFileTxt(fileName);
        addUsersToFile(fileName);
    }

    public void deleteFile(final String fileName) throws IOException {
        final File sourceFile = new File("resources\\" + fileName + ".txt");
        sourceFile.delete();
    }


}
