package org.example.weather.exception.jdbc;

public class DataAccessException extends JdbcException {
    public DataAccessException(String message) {
        super(message);
    }
}
