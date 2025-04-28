package org.example.weather.repository;

import org.example.weather.domain.City;
import org.example.weather.repository.criteria.SearchCriteria;

import java.sql.SQLException;
import java.util.List;

public interface CityRepository {
    void add(City city) throws SQLException;
    void remove(String name) throws SQLException;
    void clear();
    boolean isEmpty();
    int count();
    List<City> findByCriteria(SearchCriteria<City> criteria);
    List<City> getAllCities();
    boolean existsByName(String name);
}
