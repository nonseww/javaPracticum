package org.example.model;

import org.example.model.exceptions.BlankCityNameException;
import org.example.model.exceptions.InvalidCityNameException;
import org.example.model.exceptions.NullCityNameException;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс представляет собой город и температуру в нём
 */
public class City {
    private final String name;
    private int temperature;

    /**
     * Создаёт город с указанным названием и случайной температурой
     * в диапазоне -30 до 30 °С
     * name Название города (только русские буквы, не null)
     * temperature Температура в городе
     * @throws NullCityNameException если name == null
     * @throws InvalidCityNameException если name состоит не только из русских букв и тире
     */

    private void initTemperature() {
        this.temperature = ThreadLocalRandom.current().nextInt(-30, 31);
    }

    private void validateName(String name) {
        if (name == null) {
            throw new NullCityNameException();
        }

        if (name.isBlank()) {
            throw new BlankCityNameException();
        }

        else if (!name.matches("[а-яА-Я- ]+")) {
            throw new InvalidCityNameException(name);
        }
    }

    public City(String name) {
        validateName(name);
        this.name = name;
        initTemperature();
    }

    public String getName() {
        return name;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return String.format("Город %s, температура сейчас %d°C", name, temperature);
    }
}
