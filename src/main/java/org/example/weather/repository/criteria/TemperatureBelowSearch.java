package org.example.weather.repository.criteria;

import org.example.weather.domain.City;

import java.util.List;

public class TemperatureBelowSearch implements SearchCriteria<City>{
    private final int temperature;

    public TemperatureBelowSearch(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean test(City city) {
        return city.getTemperature() < temperature;
    }


    @Override
    public String toSqlClause() {
        return "temperature < ?";
    }

    @Override
    public List<Object> getParameters() {
        return List.of(temperature);
    }
}
