package com.belatrix.exercise.job;

import com.belatrix.exercise.bean.JobLoggerBean;
import com.belatrix.exercise.exception.NotLogTypeFoundException;
import com.belatrix.exercise.exception.NotMessageFoundException;
import com.belatrix.exercise.exception.NotOutputLogTypeFoundException;
import com.belatrix.exercise.exception.NotOutputLogWriterFoundException;
import com.belatrix.exercise.service.JobLoggerService;
import com.belatrix.exercise.service.impl.JobLoggerServiceImpl;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.*;

public class JobLogger {

    public final static String LOG_FILE_NAME = "logFile.txt";
    public final static String FOLDER_SEPARATOR = "/";

    private JobLoggerService jobLoggerService = new JobLoggerServiceImpl();

    public void executeJob(JobLoggerBean jobLoggerBean) throws Exception {
        if (!validMessage(jobLoggerBean.getMessage())) {
            throw new NotMessageFoundException("A message has not been defined");
        }
        if (!validWriterLogOutput(jobLoggerBean)) {
            throw new NotOutputLogWriterFoundException("An output writer has not been defined");
        }
        if (!validLogType(jobLoggerBean)) {
            throw new NotLogTypeFoundException("A log type has not been defined");
        }
        if (!validLogOutputType(jobLoggerBean)) {
            throw new NotOutputLogTypeFoundException("An output log type has not been defined");
        }

        Logger logger = Logger.getLogger(JobLogger.class.getName());
        String message = getMessage(jobLoggerBean.getMessage(), jobLoggerBean.getLogError(), jobLoggerBean.getLogWarning());

        if (jobLoggerBean.getLogConsoleOutput()) {
            ConsoleHandler consoleHandler = jobLoggerService.getConsoleHandler();
            logger.addHandler(consoleHandler);
            logger.log(Level.INFO, message);
            consoleHandler.close();
        }

        if (jobLoggerBean.getLogDatabaseOutput()) {
            jobLoggerService.insertLogToDatabase(
                    message,
                    getLogType(jobLoggerBean),
                    jobLoggerBean.getDbParams());
        }

        if (jobLoggerBean.getLogFileOutput()) {
            String pathFile = jobLoggerBean.getLogFileFolder().concat(FOLDER_SEPARATOR).concat(LOG_FILE_NAME);
            boolean isFileCreated = createLogFile(pathFile);
            if (isFileCreated) {
                FileHandler fileHandler = jobLoggerService.getFileHandler(pathFile);
                logger.addHandler(fileHandler);
                logger.log(Level.INFO, message);
                fileHandler.close();
            }
        }
    }

    private String getMessage(String message, boolean logError, boolean logWarning) {
        StringBuilder sb = new StringBuilder();
        String logDate = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        if (logError) {
            sb.append("error ").append(logDate).append(" ").append(message).append(". ");
        }
        if (logWarning) {
            sb.append("warning ").append(logDate).append(" ").append(message).append(".");
        }

        return sb.toString();
    }

    private boolean validLogOutputType(JobLoggerBean jobLoggerBean) {
        return jobLoggerBean.getLogError() || jobLoggerBean.getLogWarning();
    }

    private int getLogType(JobLoggerBean jobLoggerBean) {
        int type = 0;
        if (jobLoggerBean.getLogMessageType()) {
            type = 1;
        }

        if (jobLoggerBean.getLogErrorType()) {
            type = 2;
        }

        if (jobLoggerBean.getLogWarningType()) {
            type = 3;
        }

        return type;
    }

    private boolean createLogFile(String path) {
        File logFile = new File(path);
        boolean result = false;

        try {
            if (!logFile.exists()) {
                result = logFile.createNewFile();
            } else {
                return true;
            }
        } catch (IOException e) {
            System.err.format("Error trying to create the file for logs: %s\n%s", path, e.getMessage());
        }
        return result;
    }

    private boolean validMessage(String message) {
        return message != null && !message.isEmpty();
    }

    private boolean validLogType(JobLoggerBean jobLoggerBean) {
        return (jobLoggerBean.getLogMessageType() || jobLoggerBean.getLogErrorType() || jobLoggerBean.getLogWarningType());
    }

    private boolean validWriterLogOutput(JobLoggerBean jobLoggerBean) {
        return jobLoggerBean.getLogConsoleOutput() || jobLoggerBean.getLogFileOutput() || jobLoggerBean.getLogDatabaseOutput();
    }
}
