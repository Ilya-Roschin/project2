package com.java.training.app.service;

import com.java.training.app.exception.FileServiceException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {

    private static final String FILE_PATH = "resources\\storage.txt";
    private static FileService instance;

    private FileService() {
    }

    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    public FileWriter createNewFileTxt() throws IOException {
        return new FileWriter(FILE_PATH);
    }

    public boolean deleteFile(final String fileName) {
        final File sourceFile = new File(fileName);
        return sourceFile.delete();
    }

    public void readFile() {
        fileToStringList(FILE_PATH).forEach(System.out::println);
    }

    public List<String> fileToStringList(final String fileName) {
        try (final Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        } catch (final IOException e) {
            throw new FileServiceException("File with this " + fileName + "not found.", e.getCause());
        }
    }
}
