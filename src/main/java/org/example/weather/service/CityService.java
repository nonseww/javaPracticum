package org.example.weather.service;

import org.example.weather.domain.City;
import org.example.weather.repository.criteria.SearchCriteria;
import java.util.List;

public interface CityService {
    void addCities(String[] names);

    void removeCities(String[] names);

    int getCount();

    public List<City> getAllCities();

    public List<City> findCitiesByCriteria(SearchCriteria searchCriteria);

    void clear();
}
