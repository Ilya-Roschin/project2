package com.java.training.app.reader;

import com.java.training.app.service.FileService;

import java.util.Scanner;

public class Reader {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static Reader instance;

    private Reader() {
    }

    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    public String readLine(final String message) {
        System.out.println(message);
        return SCANNER.nextLine();
    }

    public int readInt(final String message) {
        System.out.println(message);
        return SCANNER.nextInt();
    }
}
