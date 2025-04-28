package org.example.weather.domain;

import org.jetbrains.annotations.NotNull;

public class City implements Comparable<City>{
    private final String name;
    private final int temperature;

    public City(String name, int temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public int compareTo(@NotNull City other) {
        return this.name.compareTo(other.getName());
    }
}
