package org.example.weather.service.jdbc;

import org.example.weather.domain.City;
import org.example.weather.domain.factory.CityFactory;
import org.example.weather.exception.jdbc.JdbcException;
import org.example.weather.repository.criteria.SearchCriteria;
import org.example.weather.repository.jdbc.JdbcCityRepository;
import org.example.weather.service.CityService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JdbcCityService implements CityService {
    private final JdbcCityRepository cityRepository;
    private final CityFactory cityFactory;

    public JdbcCityService(JdbcCityRepository cityRepository,
                           CityFactory cityFactory) {
        this.cityRepository = cityRepository;
        this.cityFactory = cityFactory;
    }

    public void addCities(String[] names) {
        Arrays.stream(names)
                .forEach(name -> {
                    City city = cityFactory.createCity(name);
                    try {
                        cityRepository.add(city);
                    } catch (SQLException e) {
                        throw new JdbcException(e.getMessage());
                    }
                });
    }

    public void removeCities(String[] names) {
        Arrays.stream(names)
                .forEach(cityRepository::remove);
    }

    public int getCount() throws SQLException {
        return cityRepository.count();
    }

    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }

    public List<City> findCitiesByCriteria(SearchCriteria searchCriteria) {
        return cityRepository.findByCriteria(searchCriteria);
    }

    public void clear() {
        cityRepository.clear();
    }
}
