package org.example.model.Cities;

import org.example.model.City.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс представляет собой список объектов класса City.
 * Полностью инкапсулирует логику сохранения нового объекта-города,
 * его поиск (для исключения дублирования), удаления и получения
 * необходимой информации как по одному городу, так и по выборке по некоторому критерию
 */
public class Cities {
    private ArrayList<City> cityList = new ArrayList<>();
    /**
     * При создании объявляет пустой ArrayList.
     * Инициализацию можно провести без параметров, с одним City или с ArrayList<City>.
     * @see #getAllCities возвращает cityList - список всех объектов City
     * @see #find(String) возвращает объект City с указанным названием города
     * @see #formatCityInfo(City) возвращает отформатированный вывод для объекта City
     */

    public Cities() {}
    public Cities(City city) {
        this();
        this.cityList.add(city);
    }
    public Cities(City ...cities) {
        this();
        if (cities != null) {
            this.cityList.addAll(List.of(cities));
        }
    }

    private ArrayList<City> getAllCities() {
        return cityList;
    }

    private String formatCityInfo(City city) {
        return String.format("Город %s, температура сейчас %d°C", city.getName(), city.getTemperature());
    }

    public City find(String name) {
        return cityList.stream()
                .filter(city -> Objects.equals(city.getName(), name))
                .findFirst()
                .orElse(null);
    }

//    public ArrayList<City> find(int temperature) {
//        return cityList.stream()
//                .filter(city -> Objects.equals(city.getTemperature(), temperature));
//    }

    @Override
    public String toString() {
        return getAllCities().stream()
                .map(this::formatCityInfo)
                .collect(Collectors.joining("\n"));
    }
}
