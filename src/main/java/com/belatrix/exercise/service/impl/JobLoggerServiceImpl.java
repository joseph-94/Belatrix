package com.belatrix.exercise.service.impl;

import com.belatrix.exercise.dao.JobLoggerDAO;
import com.belatrix.exercise.dao.impl.JobLoggerDAOImpl;
import com.belatrix.exercise.exception.FileHandlerException;
import com.belatrix.exercise.service.JobLoggerService;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public class JobLoggerServiceImpl implements JobLoggerService {

    public void insertLogToDatabase(String message, int logType, Map dbProperties) {
        JobLoggerDAO jobLoggerDAO = new JobLoggerDAOImpl();
        jobLoggerDAO.insertLog(message, logType, dbProperties);
    }

    public ConsoleHandler getConsoleHandler() {
        return new ConsoleHandler();
    }

    public FileHandler getFileHandler(String path) {
        try {
            return new FileHandler(path);
        } catch (IOException | InvalidPathException e) {
            throw new FileHandlerException("Error trying to define the file handler for logs: " + path, e);
        }
    }
}
