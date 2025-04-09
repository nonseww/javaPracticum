package org.example;

import org.example.model.Cities.Cities;
import org.example.model.City.City;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            City city1 = new City("Саратов");
            City city2 = new City("Москва");
            Cities cities = new Cities(city1, city2);
            System.out.println(cities);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addCity(String name) {

    }
}