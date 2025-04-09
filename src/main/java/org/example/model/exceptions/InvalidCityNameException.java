package org.example.model.exceptions;

public class InvalidCityNameException extends CityValidationException {
    public InvalidCityNameException(String name) {
        super(String.format("Город: %s. Название города может состоять только из русских букв и тире", name));
    }
}
