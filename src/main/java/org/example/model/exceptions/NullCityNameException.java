package org.example.model.exceptions;

public class NullCityNameException extends RuntimeException {
    public NullCityNameException() {
        super("Название города не может быть null");
    }
}
