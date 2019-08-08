package com.belatrix.exercise.bean;

import java.util.Map;

public class JobLoggerBean {

    private final String message;
    private final boolean logError;
    private final boolean logWarning;
    private final boolean logFileOutput;
    private final boolean logDatabaseOutput;
    private final boolean logConsoleOutput;
    private final boolean logMessageType;
    private final boolean logWarningType;
    private final boolean logErrorType;
    private final String logFileFolder;
    private final Map dbParams;

    public static class Builder {
        private final String message;
        private final boolean logWarning;
        private final boolean logError;
        private boolean logFileOutput;
        private boolean logDatabaseOutput;
        private boolean logConsoleOutput;
        private boolean logMessageType;
        private boolean logWarningType;
        private boolean logErrorType;
        private String logFileFolder;
        private Map dbParams;

        public Builder(String message, boolean logWarning, boolean logError) {
            this.message = message;
            this.logWarning = logWarning;
            this.logError = logError;
        }

        public Builder fileOutput(boolean val) {
            logFileOutput = val;
            return this;
        }

        public Builder databaseOutput(boolean val) {
            logDatabaseOutput = val;
            return this;
        }

        public Builder consoleOutput(boolean val) {
            logConsoleOutput = val;
            return this;
        }

        public Builder messageType(boolean val) {
            logMessageType = val;
            return this;
        }

        public Builder warningType(boolean val) {
            logWarningType = val;
            return this;
        }

        public Builder errorType(boolean val) {
            logErrorType = val;
            return this;
        }

        public Builder dbParams(Map val) {
            dbParams = val;
            return this;
        }

        public Builder logFileFolder(String val) {
            logFileFolder = val;
            return this;
        }

        public JobLoggerBean build() {
            return new JobLoggerBean(this);
        }
    }

    private JobLoggerBean(Builder builder) {
        message = builder.message;
        logError = builder.logError;
        logWarning = builder.logWarning;
        logFileOutput = builder.logFileOutput;
        logDatabaseOutput = builder.logDatabaseOutput;
        logConsoleOutput = builder.logConsoleOutput;
        logMessageType = builder.logMessageType;
        logWarningType = builder.logWarningType;
        logErrorType = builder.logErrorType;
        dbParams = builder.dbParams;
        logFileFolder = builder.logFileFolder;
    }

    public String getMessage() {
        return message;
    }

    public boolean getLogError() {
        return logError;
    }

    public boolean getLogWarning() {
        return logWarning;
    }

    public boolean getLogFileOutput() {
        return logFileOutput;
    }

    public boolean getLogDatabaseOutput() {
        return logDatabaseOutput;
    }

    public boolean getLogConsoleOutput() {
        return logConsoleOutput;
    }

    public boolean getLogMessageType() {
        return logMessageType;
    }

    public boolean getLogWarningType() {
        return logWarningType;
    }

    public boolean getLogErrorType() {
        return logErrorType;
    }

    public String getLogFileFolder() {
        return logFileFolder;
    }

    public Map getDbParams() {
        return dbParams;
    }
}
