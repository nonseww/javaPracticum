package org.example.model.Cities.exceptions;

public class CityNotFoundException extends CitiesValidationException {
    public CityNotFoundException() {
        super("Ошибка: Указанный город не был найден!");
    }
}
