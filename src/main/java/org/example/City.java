package org.example;

/**
 * Класс представляет собой город и температуру в нём
 */
public class City {
    private final String name;
    private final int temperature = 0;

    public City(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Город %s, температура сейчас %d°C", name, temperature);
    }
}
