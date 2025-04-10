package org.example.model.Cities;

import org.example.model.City.City;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс представляет собой список объектов класса City.
 * Полностью инкапсулирует логику сохранения нового объекта-города,
 * его поиск (для исключения дублирования), удаления и получения
 * необходимой информации как по одному городу, так и по выборке по некоторому критерию
 */
public class Cities {
    private ArrayList<City> cityList = new ArrayList<>();
    private HashSet<String> existingNames = new HashSet<>();

    /**
     * При создании объявляет пустой ArrayList и HashSet.
     * Инициализацию можно провести без параметров, с одним City или со множеством City.
     * @see #cityList содержит в себе объекты City
     * @see #existingNames служит для оптимизации поиска дубликатов при добавлении элементов и содержит названия
     * существующих городов
     * @see #getAllCities() возвращает cityList - список всех объектов City
     * @see #formatCityInfo(City) возвращает отформатированный вывод для объекта City
     * @see #find(String) возвращает объект City с указанным названием города
     * @see #find(int) возвращает ArrayList из объектов City с указанной температурой
     * @see #add(City...) добавляет один или несколько объектов City в cityList
     */

    public Cities() {}

    public Cities(City city) {
        this();
        this.cityList.add(city);
        this.existingNames.add(city.getName());
    }

    public Cities(@NotNull City ...cities) {
        this();
        if (cities.length > 0) {
            this.cityList.addAll(List.of(cities));
            Arrays.stream(cities)
                    .map(City::getName)
                    .forEach(this.existingNames::add);
        }
    }

    private ArrayList<City> getAllCities() {
        return cityList;
    }

    private @NotNull String formatCityInfo(@NotNull City city) {
        return String.format("Город %s, температура сейчас %d°C", city.getName(), city.getTemperature());
    }

    public City find(@NotNull String name) {
        return cityList.stream()
                .filter(city -> Objects.equals(city.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<City> find(int temperature) {
        return cityList.parallelStream()
                .filter(city -> city.getTemperature() == temperature)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void add(@NotNull City ...cities) {
        Arrays.stream(cities)
                .filter(city -> !existingNames.contains(city.getName()))
                .forEach(city -> {
                    this.cityList.add(city);
                    this.existingNames.add(city.getName());
                });
    }

    @Override
    public String toString() {
        return getAllCities().stream()
                .map(this::formatCityInfo)
                .collect(Collectors.joining("\n"));
    }
}
