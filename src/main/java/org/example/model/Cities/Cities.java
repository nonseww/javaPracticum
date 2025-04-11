package org.example.model.Cities;

import com.sun.source.tree.Tree;
import org.example.model.Cities.exceptions.CityNotFoundException;
import org.example.model.Cities.exceptions.ZeroArgumentsException;
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
     * cityList содержит в себе объекты City.
     * existingNames служит для оптимизации поиска дубликатов при добавлении элементов и содержит названия
     * существующих городов.
     * @see #getAllCities() возвращает cityList - список всех объектов City.
     * @see #formatCityInfo(City) возвращает отформатированный вывод для объекта City.
     * @see #formatName(String) возвращает отформатированное название города.
     * @see #isHere(String) возвращает true или false в зависимости от того, существует ли город с указанным названием.
     * @see #find(String) возвращает объект City с указанным названием города.
     * @see #find(int) возвращает TreeSet из объектов City с указанной температурой.
     * @see #add(String[]) создаёт по каждому названию города объект City и добавляет его в cityList.
     * @see #add(City...) добавляет один или несколько объектов City в cityList.
     * @see #add(ArrayList) добавляет все объекты City из ArrayList.
     * @see #delete(City...) удаляет все объекты City, непосредственно переданные в аргументе. Если объекта нет в
     * cityList, игнорирует его.
     * @see #delete(String[]) удаляет объекты City, у которого название совпадает с переданным в аргументе. Если города
     * с таким названием нет, то игнорирует действие.
     * @see #clear() очищает список cityList и existingNames.
     * @see #isEmpty() проверяет список cityList на пустоту.
     * @see #size() возвращает количество объектов City.
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

    @org.jetbrains.annotations.NotNull
    private String formatName(@NotNull String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    private boolean isHere(@NotNull String name) {
        String formattedName = formatName(name);
        return existingNames.contains(formattedName);
    }

    public City find(@NotNull String name) {
        if (name.isBlank()) {
            throw new ZeroArgumentsException();
        }
        String formattedName = formatName(name);
        if (!existingNames.contains(formattedName)) {
            throw new CityNotFoundException();
        }
        return cityList.stream()
                .filter(city -> Objects.equals(city.getName(), formattedName))
                .findFirst()
                .orElse(null);
    }

    public TreeSet<City> find(int temperature) {
        return cityList.parallelStream()
                .filter(city -> city.getTemperature() == temperature)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public void add(@NotNull String[] cityNames) {
        if (cityNames.length == 0 | cityNames[0].isEmpty()) {
            throw new ZeroArgumentsException();
        }
        Arrays.stream(cityNames)
                .filter(cityName -> !existingNames.contains(cityName))
                .forEach(cityName -> {
                    String formattedName = formatName(cityName);
                    cityList.add(new City(formattedName));
                    existingNames.add(formattedName);
                });
    }

    public void add(@NotNull City ...cities) {
        if (cities.length == 0) {
            throw new ZeroArgumentsException();
        }
        Arrays.stream(cities)
                .filter(city -> !existingNames.contains(city.getName()))
                .forEach(city -> {
                    cityList.add(city);
                    existingNames.add(city.getName());
                });
    }

    public void add(@NotNull ArrayList<City> cities) {
        if (cities.isEmpty()) {
            throw new ZeroArgumentsException();
        }
        cities.stream()
                .filter(city -> !existingNames.contains(city.getName()))
                .forEach(city -> {
                    cityList.add(city);
                    existingNames.add(city.getName());
                });
    }

    public void delete(@NotNull City ...cities) {
        if (cities.length == 0) {
            throw new ZeroArgumentsException();
        }
        Arrays.stream(cities)
                .filter(cityList::contains)
                .forEach(city -> {
                    cityList.remove(city);
                    existingNames.remove(city.getName());
                });
    }

    public void delete(@NotNull String[] names) {
        if (names.length == 0 || names[0].isBlank()) {
            throw new ZeroArgumentsException();
        }
        Arrays.stream(names)
                .forEach(cityName -> {
                    String formattedName = formatName(cityName);
                    if (isHere(formattedName)) {
                        cityList.remove(find(formattedName));
                        existingNames.remove(formattedName);
                    }
                });
    }

    public void clear() {
        existingNames.clear();
        cityList.clear();
    }

    public boolean isEmpty() {
        return cityList.isEmpty();
    }

    public int size() {
        return cityList.size();
    }

    @Override
    public String toString() {
        return getAllCities().stream()
                .map(this::formatCityInfo)
                .collect(Collectors.joining("\n"));
    }
}
