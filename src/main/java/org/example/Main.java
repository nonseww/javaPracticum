package org.example;

import org.example.model.Cities.Cities;
import org.example.model.City.City;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("data/data.txt");
            if (inputStream != null) {
                try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                    while(scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                    }
                }
            }
    }
}