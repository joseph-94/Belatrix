package com.belatrix.exercise.exception;

public class FileHandlerException extends RuntimeException {

    public FileHandlerException(String message, Throwable e) {
        super(message, e);
    }
}
