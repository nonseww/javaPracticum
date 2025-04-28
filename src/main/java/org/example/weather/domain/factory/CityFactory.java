package org.example.weather.domain.factory;

import org.example.weather.domain.City;
import org.example.weather.domain.formatter.NameFormatter;
import org.example.weather.domain.validator.CityDataValidator;
import java.util.concurrent.ThreadLocalRandom;

public class CityFactory {
    private final CityDataValidator validator;
    private final NameFormatter nameFormatter;

    public CityFactory(CityDataValidator validator, NameFormatter nameFormatter) {
        this.validator = validator;
        this.nameFormatter = nameFormatter;
    }

    public City createCity(String name) {
        validator.validate(name);
        int temperature = ThreadLocalRandom.current().nextInt(-30, 31);
        return new City(nameFormatter.format(name), temperature);
    }
}
