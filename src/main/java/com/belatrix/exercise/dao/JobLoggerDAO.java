package com.belatrix.exercise.dao;

import java.util.Map;

/**
 * Created on 8/4/2019.
 *
 * @author Joseph A.
 */
public interface JobLoggerDAO {

    int insertLog(String message, int logType, Map dbParams);
}
