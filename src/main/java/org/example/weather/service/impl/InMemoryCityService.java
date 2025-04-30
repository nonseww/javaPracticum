package org.example.weather.service.impl;

import org.example.weather.domain.City;
import org.example.weather.domain.factory.CityFactory;
import org.example.weather.repository.criteria.SearchCriteria;
import org.example.weather.repository.impl.InMemoryCityRepository;
import org.example.weather.service.CityService;

import java.util.Arrays;
import java.util.List;

// impl-реализация городов локально

public class InMemoryCityService implements CityService {
    private final InMemoryCityRepository cityRepository;
    private final CityFactory cityFactory;

    public InMemoryCityService(InMemoryCityRepository cityRepository,
                       CityFactory cityFactory) {
        this.cityRepository = cityRepository;
        this.cityFactory = cityFactory;
    }

    // берем город из БД и добавляем в локальный список
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
