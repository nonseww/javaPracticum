package org.example.model.Cities;

import com.sun.source.tree.Tree;
import org.example.model.City.City;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс представляет собой список объектов класса City.
 * Полностью инкапсулирует логику сохранения нового объекта-города,
 * его поиск (для исключения дублирования), удаления и получения
 * необходимой информации как по одному городу, так и по выборке по некоторому критерию
 */
public class Cities {
    private final TreeSet<City> cityList = new TreeSet<>();
    private final HashSet<String> existingNames = new HashSet<>();

    /**
     * При создании объявляет пустой TreeSet и HashSet.
     * Инициализацию можно провести без параметров, с одним City или со множеством City.
     * @see #cityList содержит в себе объекты City.
     * @see #existingNames служит для оптимизации поиска дубликатов при добавлении элементов и содержит названия
     * существующих городов.
     * @see #getAllCities() возвращает cityList - список всех объектов City.
     * @see #formatCityInfo(City) возвращает отформатированный вывод для объекта City.
     * @see #isHere(String) возвращает true или false в зависимости от того, существует ли город с указанным названием.
     * @see #find(String) возвращает объект City с указанным названием города.
     * @see #find(int) возвращает TreeSet из объектов City с указанной температурой.
     * @see #add(City...) добавляет один или несколько объектов City в cityList.
     * @see #delete(City...) удаляет все объекты City, непосредственно переданные в аргументе. Если объекта нет в
     * cityList, игнорирует его.
     * @see #delete(String) удаляет объект City, у которого название совпадает с переданным в аргументе. Если города
     * с таким названием нет, то игнорирует действие.
     */

    public Cities() {}

    public Cities(@NotNull City city) {
        this();
        cityList.add(city);
        existingNames.add(city.getName());
    }

    public Cities(@NotNull City ...cities) {
        this();
        if (cities.length > 0) {
            cityList.addAll(List.of(cities));
            Arrays.stream(cities)
                    .map(City::getName)
                    .forEach(existingNames::add);
        }
    }

    private TreeSet<City> getAllCities() {
        return cityList;
    }

    private @NotNull String formatCityInfo(@NotNull City city) {
        return String.format("Город %s, температура сейчас %d°C", city.getName(), city.getTemperature());
    }

    private boolean isHere(@NotNull String name) {
        return existingNames.contains(name);
    }

    public City find(@NotNull String name) {
        return cityList.stream()
                .filter(city -> Objects.equals(city.getName(), name))
                .findFirst()
                .orElse(null);
    }

    public TreeSet<City> find(int temperature) {
        return cityList.parallelStream()
                .filter(city -> city.getTemperature() == temperature)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public void add(@NotNull City ...cities) {
        Arrays.stream(cities)
                .filter(city -> !existingNames.contains(city.getName()))
                .forEach(city -> {
                    cityList.add(city);
                    existingNames.add(city.getName());
                });
    }

    public void delete(@NotNull City ...cities) {
        Arrays.stream(cities)
                .filter(cityList::contains)
                .forEach(city -> {
                    cityList.remove(city);
                    existingNames.remove(city.getName());
                });
    }

    public void delete(@NotNull String name) {
        if (isHere(name)) {
            cityList.remove(find(name));
        }
    }

    @Override
    public String toString() {
        return getAllCities().stream()
                .map(this::formatCityInfo)
                .collect(Collectors.joining("\n"));
    }
}
