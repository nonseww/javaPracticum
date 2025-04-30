package org.example.weather.exception.jdbc;

public class DuplicateCityException extends JdbcException {
    public DuplicateCityException(String name) {
        super(String.format("Город с названием %s уже существует в базе данных!", name));
    }
}
