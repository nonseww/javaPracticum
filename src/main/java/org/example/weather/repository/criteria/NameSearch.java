package org.example.weather.repository.criteria;

import org.example.weather.domain.City;
import org.jetbrains.annotations.NotNull;

public class NameSearch implements SearchCriteria{
    private final String name;

    public NameSearch(String name) {
        this.name = name;
    }

    @Override
    public boolean test(@NotNull City city) {
        return city.getName().equalsIgnoreCase(name);
    }
}
