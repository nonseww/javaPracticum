package org.example.weather.exception.cityRepository;

public class CityNotFoundException extends CityRepositoryValidationException {
    public CityNotFoundException(String name) {
        super(String.format("Город %s не был найден!", name));
    }
}
