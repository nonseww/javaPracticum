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
 * необходимой информации
 */
public class Cities {
    private ArrayList<City> cityList = new ArrayList<>();
    /**
     * При создании объявляет пустой ArrayList.
     * Инициализацию можно провести без параметров, с одним City или с ArrayList<City>.
     */


    private ArrayList<City> getAllCities() {
        return cityList;
    }

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

    private String formatCityInfo(City city) {
        return String.format("Город %s, температура сейчас %d°C", city.getName(), city.getTemperature());
    }

    @Override
    public String toString() {
        return getAllCities().stream()
                .map(this::formatCityInfo)
                .collect(Collectors.joining("\n"));
    }
}
