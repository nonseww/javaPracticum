package org.example.weather.service;

import org.example.weather.domain.City;
import org.example.weather.domain.factory.CityFactory;
import org.example.weather.repository.CityRepository;
import org.example.weather.repository.criteria.SearchCriteria;

import java.util.Arrays;
import java.util.List;

public class CityService {
    private final CityRepository cityRepository;
    private final CityFactory cityFactory;

    public CityService(CityRepository cityRepository,
                       CityFactory cityFactory) {
        this.cityRepository = cityRepository;
        this.cityFactory = cityFactory;
    }

    public void addCities(String[] names) {
        Arrays.stream(names)
                .forEach(name -> {
                    City city = cityFactory.createCity(name);
                    cityRepository.add(city);
                });
    }

    public void removeCities(String[] names) {
        Arrays.stream(names)
                        .forEach(cityRepository::remove);
    }

    public int getCount() {
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
