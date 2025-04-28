package org.example.weather.formatter;

import org.example.weather.domain.City;
import org.jetbrains.annotations.NotNull;

public interface CityFormatter {
    public String format(@NotNull City city);
}
