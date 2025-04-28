package org.example.weather.exception.jdbc;

public class CloseConnectionException extends JdbcException {
    public CloseConnectionException(String message) {
        super("Ошибка закрытия соединения, " + message);
    }
}
