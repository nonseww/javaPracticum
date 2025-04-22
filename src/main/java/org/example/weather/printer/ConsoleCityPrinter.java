package org.example.weather.printer;

import org.example.weather.domain.City;
import org.example.weather.formatter.TextCityFormatter;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;
import java.util.List;

public class ConsoleCityPrinter implements CityPrinter{
    private final TextCityFormatter textCityFormatter;

    public ConsoleCityPrinter(TextCityFormatter textCityFormatter) {
        this.textCityFormatter = textCityFormatter;
    }

    public void print(@NotNull List<City> cities) {
        cities.stream()
                .map(textCityFormatter::format)
                .forEach(System.out::println);
    }

    public void print(@NotNull City city) {
        System.out.println(textCityFormatter.format(city));
    }
}
