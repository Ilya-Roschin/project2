package com.java.training.app.menu;

import com.java.training.app.service.FileService;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private static final FileService FILE_SERVICE = FileService.getInstance();
    private static final ApplicationMenu APPLICATION_MENU = ApplicationMenu.getInstance();

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
                APPLICATION_MENU.addUser();
                break;
            case 2:
                APPLICATION_MENU.findUser();
                break;
            case 3:
                APPLICATION_MENU.deleteUser();
                break;
            case 4:
                APPLICATION_MENU.findAllUsers();
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
}

