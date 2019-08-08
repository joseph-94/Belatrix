package com.belatrix.exercise.bean;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JobLoggerBeanTest {

    public static final String PARAM_MESSAGE = "message";
    public static final String LOG_FILE_FOLFER_NAME = "LogFileFolder";
    public static final String DB_PARAM_DBMS_KEY = "dbms";
    public static final String DB_PARAM_DBMS_VALUE = "mysql";
    public static final String DB_PARAM_SERVERNAME_KEY = "serverName";
    public static final String DB_PARAM_SERVERNAME_VALUE = "localhost";
    public static final String DB_PARAM_PORTNUMBER_KEY = "portNumber";
    public static final String DB_PARAM_PORTNUMBER_VALUE = "3306";
    public static final String DB_PARAM_USER_KEY = "user";
    public static final String DB_PARAM_USER_VALUE = "root";
    public static final String DB_PARAM_PASSWORD_KEY = "password";
    public static final String DB_PARAM_PASSWORD_VALUE = "sql";
    public static final Map DB_PARAMS = new HashMap<String, String>() {{
        put(DB_PARAM_DBMS_KEY, DB_PARAM_DBMS_VALUE);
        put(DB_PARAM_SERVERNAME_KEY, DB_PARAM_SERVERNAME_VALUE);
        put(DB_PARAM_PORTNUMBER_KEY, DB_PARAM_PORTNUMBER_VALUE);
        put(DB_PARAM_USER_KEY, DB_PARAM_USER_VALUE);
        put(DB_PARAM_PASSWORD_KEY, DB_PARAM_PASSWORD_VALUE);
    }};

    @Test
    public void jobLoggerBuilderTest() {
        JobLoggerBean jobLogger = new JobLoggerBean.Builder(PARAM_MESSAGE, true, true)
                .consoleOutput(true)
                .databaseOutput(true)
                .fileOutput(true)
                .messageType(true)
                .errorType(true)
                .warningType(true)
                .logFileFolder(LOG_FILE_FOLFER_NAME)
                .dbParams(DB_PARAMS)
                .build();

        assertNotNull(jobLogger);
        assertNotNull(jobLogger.getMessage());
        assertNotNull(jobLogger.getLogFileFolder());
        assertNotNull(jobLogger.getLogWarning());
        assertNotNull(jobLogger.getLogError());
        assertNotNull(jobLogger.getLogConsoleOutput());
        assertNotNull(jobLogger.getLogDatabaseOutput());
        assertNotNull(jobLogger.getLogFileOutput());
        assertNotNull(jobLogger.getLogMessageType());
        assertNotNull(jobLogger.getLogWarningType());
        assertNotNull(jobLogger.getLogErrorType());
        assertNotNull(jobLogger.getDbParams());

        assertEquals(PARAM_MESSAGE, jobLogger.getMessage());
        assertEquals(LOG_FILE_FOLFER_NAME, jobLogger.getLogFileFolder());
        assertTrue(jobLogger.getLogError());
        assertTrue(jobLogger.getLogWarning());
        assertTrue(jobLogger.getLogConsoleOutput());
        assertTrue(jobLogger.getLogDatabaseOutput());
        assertTrue(jobLogger.getLogFileOutput());
        assertTrue(jobLogger.getLogMessageType());
        assertTrue(jobLogger.getLogWarningType());
        assertTrue(jobLogger.getLogErrorType());
        assertEquals(jobLogger.getDbParams().get(DB_PARAM_DBMS_KEY), DB_PARAM_DBMS_VALUE);
        assertEquals(jobLogger.getDbParams().get(DB_PARAM_SERVERNAME_KEY), DB_PARAM_SERVERNAME_VALUE);
        assertEquals(jobLogger.getDbParams().get(DB_PARAM_PORTNUMBER_KEY), DB_PARAM_PORTNUMBER_VALUE);
        assertEquals(jobLogger.getDbParams().get(DB_PARAM_USER_KEY), DB_PARAM_USER_VALUE);
        assertEquals(jobLogger.getDbParams().get(DB_PARAM_PASSWORD_KEY), DB_PARAM_PASSWORD_VALUE);
    }

    @Test
    public void jobLoggerBuilderWithoutOptionalParametersTest() {
        JobLoggerBean jobLogger = new JobLoggerBean.Builder(PARAM_MESSAGE, true, true)
                .build();

        assertNotNull(jobLogger);
        assertNull(jobLogger.getLogFileFolder());
        assertNull(jobLogger.getDbParams());
        assertNotNull(jobLogger.getMessage());
        assertNotNull(jobLogger.getLogWarning());
        assertNotNull(jobLogger.getLogError());
        assertNotNull(jobLogger.getLogConsoleOutput());
        assertNotNull(jobLogger.getLogDatabaseOutput());
        assertNotNull(jobLogger.getLogFileOutput());
        assertNotNull(jobLogger.getLogMessageType());
        assertNotNull(jobLogger.getLogWarningType());
        assertNotNull(jobLogger.getLogErrorType());

        assertEquals(PARAM_MESSAGE, jobLogger.getMessage());
        assertTrue(jobLogger.getLogError());
        assertTrue(jobLogger.getLogWarning());
        assertFalse(jobLogger.getLogConsoleOutput());
        assertFalse(jobLogger.getLogDatabaseOutput());
        assertFalse(jobLogger.getLogFileOutput());
        assertFalse(jobLogger.getLogMessageType());
        assertFalse(jobLogger.getLogWarningType());
        assertFalse(jobLogger.getLogErrorType());
    }

}
