package org.example;

import org.example.model.City;

public class Main {
    public static void main(String[] args) {
        City city1 = new City("Саратов");
        City city2 = new City("Москва");
        System.out.println(city1);
        System.out.println(city2);
        System.out.println(city1);
        City city3 = new City("");
        City city4 = new City("bbb");
    }
}