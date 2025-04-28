package org.example.weather.exception.cityRepository;

public class CityRepositoryValidationException extends RuntimeException {
    public CityRepositoryValidationException(String message) {
        super(message);
    }
}
