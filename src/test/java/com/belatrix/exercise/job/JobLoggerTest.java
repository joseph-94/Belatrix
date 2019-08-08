package com.belatrix.exercise.job;

import com.belatrix.exercise.bean.JobLoggerBean;
import com.belatrix.exercise.exception.NotLogTypeFoundException;
import com.belatrix.exercise.exception.NotMessageFoundException;
import com.belatrix.exercise.exception.NotOutputLogWriterFoundException;
import com.belatrix.exercise.util.UtilMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static com.belatrix.exercise.bean.JobLoggerBeanTest.PARAM_MESSAGE;
import static com.belatrix.exercise.job.JobLogger.LOG_FILE_NAME;
import static com.belatrix.exercise.util.UtilMock.getContentFile;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class JobLoggerTest {

    @Test
    public void executeJobWithWarningAndErrorFilelogResult() throws Exception {
        JobLoggerBean jobLoggerBean = new JobLoggerBean.Builder(PARAM_MESSAGE, true, true)
                .fileOutput(true)
                .warningType(true)
                .logFileFolder(UtilMock.getDefaultFolder("WarningAndError"))
                .build();

        JobLogger jobLogger = new JobLogger();
        jobLogger.executeJob(jobLoggerBean);

        String logfileContent = getContentFile(jobLoggerBean.getLogFileFolder().concat(String.valueOf(File.separatorChar)).concat(LOG_FILE_NAME));
        assertNotNull(logfileContent);
        assertTrue(!logfileContent.isEmpty());
        assertTrue(logfileContent.contains("error"));
        assertTrue(logfileContent.contains("warning"));
    }

    @Test
    public void executeJobWithErrorFilelogResult() throws Exception {
        JobLoggerBean jobLoggerBean = new JobLoggerBean.Builder(PARAM_MESSAGE, false, true)
                .fileOutput(true)
                .errorType(true)
                .logFileFolder(UtilMock.getDefaultFolder("Error"))
                .build();

        JobLogger jobLogger = new JobLogger();
        jobLogger.executeJob(jobLoggerBean);

        String logfileContent = getContentFile(jobLoggerBean.getLogFileFolder().concat(String.valueOf(File.separatorChar)).concat(LOG_FILE_NAME));
        assertNotNull(logfileContent);
        assertTrue(!logfileContent.isEmpty());
        assertTrue(logfileContent.contains("error"));
        assertFalse(logfileContent.contains("warning"));
    }

    @Test
    public void executeJobWithWarningFilelogResult() throws Exception {
        JobLoggerBean jobLoggerBean = new JobLoggerBean.Builder(PARAM_MESSAGE, true, false)
                .fileOutput(true)
                .warningType(true)
                .logFileFolder(UtilMock.getDefaultFolder("Warning"))
                .build();

        JobLogger jobLogger = new JobLogger();
        jobLogger.executeJob(jobLoggerBean);

        String logfileContent = getContentFile(jobLoggerBean.getLogFileFolder().concat(String.valueOf(File.separatorChar)).concat(LOG_FILE_NAME));
        assertNotNull(logfileContent);
        assertTrue(!logfileContent.isEmpty());
        assertTrue(logfileContent.contains("warning"));
        assertFalse(logfileContent.contains("error"));
    }


    @Test(expected = NotOutputLogWriterFoundException.class)
    public void executeJobWithNotTypeDefinedExceptionTest() throws Exception {
        JobLoggerBean jobLoggerBean = new JobLoggerBean.Builder(PARAM_MESSAGE, true, true)
                .build();

        JobLogger jobLogger = new JobLogger();
        jobLogger.executeJob(jobLoggerBean);
    }

    @Test(expected = NotMessageFoundException.class)
    public void executeJobWithNullMessageTest() throws Exception {
        JobLoggerBean jobLoggerBean = new JobLoggerBean.Builder(null, true, true)
                .build();

        JobLogger jobLogger = new JobLogger();
        jobLogger.executeJob(jobLoggerBean);
    }

    @Test(expected = NotMessageFoundException.class)
    public void executeJobWithEmptyMessageTest() throws Exception {
        JobLoggerBean jobLoggerBean = new JobLoggerBean.Builder("", true, true)
                .build();

        JobLogger jobLogger = new JobLogger();
        jobLogger.executeJob(jobLoggerBean);
    }

    @Test(expected = NotLogTypeFoundException.class)
    public void executeJobWithOutputLogExceptionTest() throws Exception {
        JobLoggerBean jobLoggerBean = new JobLoggerBean.Builder(PARAM_MESSAGE, false, false)
                .consoleOutput(true)
                .build();

        JobLogger jobLogger = new JobLogger();
        jobLogger.executeJob(jobLoggerBean);
    }
}
