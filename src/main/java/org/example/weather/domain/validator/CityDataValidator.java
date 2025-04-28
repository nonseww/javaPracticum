package org.example.weather.domain.validator;

import org.example.weather.exception.city.BlankCityNameException;
import org.example.weather.exception.city.InvalidCityNameException;
import org.example.weather.exception.city.NullCityNameException;

public class CityDataValidator {
    public void validate(String name) {
        if (name == null) throw new NullCityNameException();
        if (name.isBlank()) throw new BlankCityNameException();
        if (!name.matches("[а-яА-Я- ]+")) throw new InvalidCityNameException(name);
    }
}
