package org.example.model.City.exceptions;

public class InvalidCityNameException extends CityValidationException {
    public InvalidCityNameException(String name) {
        super(String.format("Город: %s. Название города может состоять только из русских букв и тире", name));
    }
}
