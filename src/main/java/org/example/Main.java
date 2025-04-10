package org.example;

import org.example.model.Cities.Cities;
import org.example.model.City.City;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            City city1 = new City("Саратов");
            City city2 = new City("Москва");
            City city3 = new City("Питер");
            Cities cities = new Cities(city1);
            cities.add(city2, city3);
            City city4 = new City("Сар");
            cities.delete("ааа");
            cities.delete(city4);
            System.out.println(cities);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addCity(String name) {

    }
}