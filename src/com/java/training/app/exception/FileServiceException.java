package com.java.training.app.exception;

public class FileServiceException extends RuntimeException{

    public FileServiceException(final String message) {
        super(message);
    }

    public FileServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
