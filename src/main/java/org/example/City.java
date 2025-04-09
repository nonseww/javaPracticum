package org.example;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Класс представляет собой город и температуру в нём
 */
public class City {
    private final String name;
    private int temperature;

    private void initTemperature() {
        this.temperature = ThreadLocalRandom.current().nextInt(-30, 31);
    }

    public City(String name) {
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
