package org.example.weather.exception.jdbc;

public class InitialException extends JdbcException {
  public InitialException(String message) {
    super("Ошибка инициализации БД: " + message);
  }
}
