package com.java.training.app.file;

import com.java.training.app.storage.Storage;
import com.java.training.app.reader.Reader;

import java.io.IOException;

public class File {

    private static final Reader READER = new Reader();
    private static final Storage STORAGE = new Storage();
    private static final String TEXT = "Enter file name: ";

    public void createNewFileTxt() throws IOException {
        final String fileName = READER.readLine(TEXT);
        STORAGE.createNewFileTxt(fileName);
    }

    public void addTextToFile() throws IOException {
        final String fileName = READER.readLine(TEXT);
        STORAGE.addUsersToFile(fileName);
    }

    public void readFile() throws IOException {
        final String fileName = READER.readLine(TEXT);
        STORAGE.readFile(fileName);
    }

    public void updateFile() throws IOException {
        final String fileName = READER.readLine(TEXT);
        STORAGE.updateFile(fileName);
    }

    public void deleteFile() throws IOException {
        final String fileName = READER.readLine(TEXT);
        STORAGE.deleteFile(fileName);
    }




}
