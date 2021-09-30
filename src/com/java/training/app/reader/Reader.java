package com.java.training.app.reader;

import java.util.Scanner;

public class Reader {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String readLine(String message) {
        System.out.println(message);
        return SCANNER.nextLine();
    }

    public int readInt(String message) {
        System.out.println(message);
        return SCANNER.nextInt();
    }

}
