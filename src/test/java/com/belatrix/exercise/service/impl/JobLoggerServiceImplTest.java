package com.belatrix.exercise.service.impl;

import com.belatrix.exercise.exception.FileHandlerException;
import com.belatrix.exercise.service.JobLoggerService;
import com.belatrix.exercise.util.UtilMock;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;

import static com.belatrix.exercise.job.JobLogger.FOLDER_SEPARATOR;
import static com.belatrix.exercise.job.JobLogger.LOG_FILE_NAME;
import static org.junit.Assert.*;

public class JobLoggerServiceImplTest {

    JobLoggerService jobLoggerService;

    @Before
    public void setUp() {
        jobLoggerService = new JobLoggerServiceImpl();
    }

    @Test
    public void logConsoleHandlerTest() {
        ConsoleHandler handler = jobLoggerService.getConsoleHandler();

        assertNotNull(handler);
    }

    @Test
    public void logFileHandlerTest() throws URISyntaxException {
        FileHandler handler = jobLoggerService.getFileHandler(UtilMock.getDefaultFolder("handler").concat(FOLDER_SEPARATOR).concat(LOG_FILE_NAME));

        assertNotNull(handler);
    }

    @Test(expected = FileHandlerException.class)
    public void logFileHandlerExceptionTest() {
        jobLoggerService.getFileHandler("//");
    }
}
