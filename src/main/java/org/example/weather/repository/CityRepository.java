package org.example.weather.repository;

import org.example.weather.domain.City;
import org.example.weather.repository.criteria.SearchCriteria;

import java.util.List;
import java.util.Optional;

public interface CityRepository {
    void add(City city);
    void remove(String name);
    void clear();
    boolean isEmpty();
    int count();
    Optional<City> findByName(String name);
    List<City> findByCriteria(SearchCriteria criteria);
    List<City> getAllCities();
    boolean existsByName(String name);
}
