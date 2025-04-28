package org.example.weather.exception.city;

public class NullCityNameException extends RuntimeException {
    public NullCityNameException() {
        super("Название города не может быть null!");
    }
}
