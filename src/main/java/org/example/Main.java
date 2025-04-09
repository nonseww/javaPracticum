package org.example;

import org.example.model.City;

public class Main {
    public static void main(String[] args) {
        try {
            City city1 = new City("сараТов");
            City city2 = new City("МосКВа");
            System.out.println(city1);
            System.out.println(city2);
            System.out.println(city1);
            City city3 = new City("");
            City city4 = new City("fhhdjvm");
            City city5 = new City("Саратов227");
            System.out.println(city3);
            System.out.println(city4);
            System.out.println(city5);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}