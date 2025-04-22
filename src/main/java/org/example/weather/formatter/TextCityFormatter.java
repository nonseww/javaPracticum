package org.example.weather.formatter;

import org.example.weather.domain.City;
import org.jetbrains.annotations.NotNull;

public class TextCityFormatter implements CityFormatter {
    public String format(@NotNull City city) {
        return String.format("В городе %s погода %d°C", city.getName(), city.getTemperature());
    }
}
