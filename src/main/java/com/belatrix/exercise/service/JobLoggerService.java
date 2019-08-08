package com.belatrix.exercise.service;

import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

public interface JobLoggerService {

    void insertLogToDatabase(String message, int logType, Map dbProperties);

    ConsoleHandler getConsoleHandler();

    FileHandler getFileHandler(String path);
}
