package org.example.weather.exception.cityRepository;

public class DuplicateCityException extends CityRepositoryValidationException {
    public DuplicateCityException(String name) {
        super(String.format("Город %s уже существует, нельзя добавить!", name));
    }
}
