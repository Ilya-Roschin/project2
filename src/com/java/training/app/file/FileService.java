package com.java.training.app.file;

import com.java.training.app.storage.Storage;
import com.java.training.app.reader.Reader;
import java.io.IOException;

public class FileService {

    private static final Reader READER = new Reader();
    private static final Storage STORAGE = new Storage();
    private static final String ENTER_FILE_NAME = "Enter file name: ";

    public void createNewFileTxt() throws IOException {
        final String fileName = READER.readLine(ENTER_FILE_NAME);
        STORAGE.createNewFileTxt(fileName);
    }

    public void addTextToFile() throws IOException {
        final String fileName = READER.readLine(ENTER_FILE_NAME);
        STORAGE.addUsersToFile(fileName);
    }

    public void readFile() throws IOException {
        final String fileName = READER.readLine(ENTER_FILE_NAME);
        STORAGE.readFile(fileName);
    }

    public void updateFile() throws IOException {
        final String fileName = READER.readLine(ENTER_FILE_NAME);
        STORAGE.updateFile(fileName);
    }

    public void deleteFile() throws IOException {
        final String fileName = READER.readLine(ENTER_FILE_NAME);
        STORAGE.deleteFile(fileName);
    }
}
