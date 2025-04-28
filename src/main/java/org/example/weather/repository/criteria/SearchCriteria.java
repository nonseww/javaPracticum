package org.example.weather.repository.criteria;

import org.example.weather.domain.City;

@FunctionalInterface
public interface SearchCriteria {
    boolean test(City city);
}
