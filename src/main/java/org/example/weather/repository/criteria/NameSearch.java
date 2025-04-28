package org.example.weather.repository.criteria;

import org.example.weather.domain.City;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NameSearch implements SearchCriteria<City>{
    private final String name;

    public NameSearch(String name) {
        this.name = name;
    }

    @Override
    public boolean test(@NotNull City city) {
        return city.getName().equalsIgnoreCase(name);
    }

    @Override
    public String toSqlClause() {
        return "LOWER(name) = ?";
    }

    @Override
    public List<Object> getParameters() {
        return List.of(name);
    }
}
