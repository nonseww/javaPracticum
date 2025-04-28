package org.example.weather.repository.impl;

import org.example.weather.domain.City;
import org.example.weather.exception.cityRepository.CityNotFoundException;
import org.example.weather.exception.cityRepository.DuplicateCityException;
import org.example.weather.repository.CityRepository;
import org.example.weather.repository.criteria.SearchCriteria;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryCityRepository implements CityRepository {
    private final Set<City> existingCities = new TreeSet<>();
    private final Set<String> existingNames = new HashSet<>();

    public InMemoryCityRepository() {}

    @Override
    public void add(@NotNull City city) {
        if (existingNames.contains(city.getName()))
            throw new DuplicateCityException(city.getName());
        existingCities.add(city);
        existingNames.add(city.getName());
    }

    @Override
    public void remove(@NotNull String name) {
        if (!existingNames.contains(name))
            throw new CityNotFoundException(name);
        existingCities.removeIf(city -> city.getName().equals(name));
        existingNames.remove(name);
    }

    @Override
    public void clear() {
        existingNames.clear();
        existingCities.clear();
    }

    @Override
    public boolean isEmpty() {
        return existingNames.isEmpty();
    }

    @Override
    public int count() {
        return existingNames.size();
    }

    @Override
    public List<City> findByCriteria(@NotNull SearchCriteria criteria) {
        return existingCities.stream()
                .filter(criteria::test)
                .collect(Collectors.toList());
    }

    @Override
    public List<City> getAllCities() {
        return Collections.unmodifiableList(new ArrayList<>(existingCities));
    }

    @Override
    public boolean existsByName(@NotNull String name) {
        return existingNames.contains(name);
    }
}
