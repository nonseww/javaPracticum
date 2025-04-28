package org.example.weather.repository.criteria;

import org.example.weather.domain.City;

public class TemperatureBelowSearch implements SearchCriteria{
    private final int temperature;

    public TemperatureBelowSearch(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean test(City city) {
        return city.getTemperature() < temperature;
    }
}
