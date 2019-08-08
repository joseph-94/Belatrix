package com.belatrix.exercise.dao.impl;

import com.belatrix.exercise.dao.JobLoggerDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class JobLoggerDAOImpl implements JobLoggerDAO {

    public Connection getConnection(Map dbProperties) {
        String jdbcUrl = "jdbc:" + dbProperties.get("dbms") + "://" + dbProperties.get("serverName") +
                ":" + dbProperties.get("portNumber") + "/" + dbProperties.get("databaseName");
        String user = String.valueOf(dbProperties.get("user"));
        String password = String.valueOf(dbProperties.get("password"));

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            return connection;
        } catch (SQLException e) {
            System.err.format("Error trying to create a database connection: %s", e.getMessage());
            return null;
        }
    }

    @Override
    public int insertLog(String message, int logType, Map dbParams) {
        String LOG_SQL_INSERT = "INSERT INTO Log_Values values (?, ?)";
        int result = 0;

        try (Connection connection = getConnection(dbParams);
             PreparedStatement preparedStatement = connection.prepareStatement(LOG_SQL_INSERT)) {

            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, logType);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("Error trying to execute sql statement with values: %s, %s, %s\n%s",
                    LOG_SQL_INSERT, message, logType, e.getMessage());
        }

        return result;
    }
}
