package org.example.model.City;

import org.example.model.City.exceptions.BlankCityNameException;
import org.example.model.City.exceptions.InvalidCityNameException;
import org.example.model.City.exceptions.NullCityNameException;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс представляет собой город с указанным названием и случайной температурой в диапазоне от -30°С до 30°С
 */
public class City implements Comparable<City> {
    private final String name;
    private int temperature;

    /**
     * Создаёт город с указанным названием и случайной температурой в диапазоне от -30°С до 30°С.
     * Класс является Comparable по полю name.
     * @see #name Название города (только русские буквы, не null).
     * @see #temperature Температура в городе.
     * @see #initTemperature() инициализирует температуру случайным значением в диапазоне от -30 до 30 °С.
     * @see #validateName(String) проверяет корректность названия города.
     * @see #formatName(String) возвращает отформатированное название города
     * (первая буква большая, остальные маленькие).
     * @see #getName() возвращает название города.
     * @see #getTemperature() возвращает температуру в городе.
     * @throws NullCityNameException если name == null.
     * @throws BlankCityNameException если name пустой.
     * @throws InvalidCityNameException если name состоит не только из русских букв и тире.
     */

    public City(String name) {
        validateName(name);
        this.name = formatName(name);
        initTemperature();
    }

    private void initTemperature() {
        this.temperature = ThreadLocalRandom.current().nextInt(-30, 31);
    }

    private void validateName(String name) {
        if (name == null) {
            throw new NullCityNameException();
        }

        if (name.isBlank()) {
            throw new BlankCityNameException();
        } else if (!name.matches("[а-яА-Я- ]+")) {
            throw new InvalidCityNameException(name);
        }
    }

    @org.jetbrains.annotations.NotNull
    private String formatName(@NotNull String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String getName() {
        return name;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return String.format("Город %s, температура сейчас %d°C", getName(), getTemperature());
    }

    @Override
    public int compareTo(City other) {
        return this.name.compareTo(other.getName());
    }
}
