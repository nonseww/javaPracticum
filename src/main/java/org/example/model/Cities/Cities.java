package org.example.model.Cities;

import org.example.model.City.City;

import java.util.ArrayList;

/**
 * Класс представляет собой список объектов класса City.
 * Полностью инкапсулирует логику сохранения нового объекта-города,
 * его поиск (для исключения дублирования), удаления и получения
 * необходимой информации
 */
public class Cities {
    private ArrayList<City> cityList = new ArrayList<>();
    /**
     * При создании объявляет пустой ArrayList
     */

    public Cities() {}
    public Cities(City city) {
        this.cityList.add(city);
    }
    public Cities(ArrayList<City> cities) {
        if (cities != null) {
            this.cityList.addAll(cities);
        }
    }
}
