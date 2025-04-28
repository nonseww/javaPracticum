package org.example.weather.printer;

import org.example.weather.domain.City;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface CityPrinter {
    public void print(@NotNull List<City> cities);
    public void print(@NotNull City city);
}
