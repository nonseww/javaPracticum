package org.example.weather.exception.cityRepository;

public class ZeroArgumentsException extends CityRepositoryValidationException {
    public ZeroArgumentsException() {
        super("Не было передано аргументов!");
    }
}
