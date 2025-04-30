package org.example.weather.utils;

import org.example.weather.config.DatabaseConfig;
import org.example.weather.exception.jdbc.CloseConnectionException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        return DatabaseConfig.getConnection();
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new CloseConnectionException(e.getMessage());
            }
        }
    }
}
