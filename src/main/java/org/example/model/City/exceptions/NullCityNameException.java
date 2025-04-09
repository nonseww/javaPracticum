package org.example.model.City.exceptions;

public class NullCityNameException extends RuntimeException {
    public NullCityNameException() {
        super("Название города не может быть null");
    }
}
