package org.example;

import org.example.model.Cities.Cities;
import org.example.model.City.City;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("data/hello.txt");
            if (inputStream != null) {
                try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                    while(scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                    }
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Нет такого файла в ресурсах!");
            }
            StringBuilder commands = new StringBuilder();
            InputStream inputStream1 = Main.class.getClassLoader().getResourceAsStream("data/commands.txt");
            if (inputStream1 != null) {
                try (Scanner scanner = new Scanner(inputStream1, StandardCharsets.UTF_8)) {
                    while(scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                        commands.append(line);
                    }
                }
            }

            try {
                Cities cities = new Cities();
                boolean isWorking = true;
                Scanner userLine = new Scanner(System.in);
                while(isWorking) {
                    String fullCommand = userLine.nextLine().trim();
                    if (fullCommand.isEmpty()) {
                        continue;
                    }
                    String[] parts = fullCommand.split("\\s+", 2);
                    String command = formatCommand(parts[0]);
                    String arguments = parts.length > 1 ? parts[1] : "";

                    switch(command) {
                        case("Добавить"):
                            if (arguments.isEmpty()) {
                                System.out.println("Ошибка: Не указаны города!");
                                break;
                            }
                            String[] cityNames = arguments.split("\\s+");
                            int countBefore = cities.size();
                            cities.add(cityNames);
                            System.out.println("Добавлено городов " + (cities.size() - countBefore));
                            break;
                        case("Информация"):
                            if (cities.isEmpty()) {
                                System.out.println("Нет городов в списке.");
                            } else {
                                System.out.println(cities);
                            }
                            break;
                        case("Информация_город"):
                            if (arguments.isEmpty()) {
                                System.out.println("Ошибка: Не указаны города!");
                                break;
                            }
                            String cityName = arguments.split("\\s+")[0];
                            City response = cities.find(cityName);
                            if (response == null) {
                                System.out.println("Ошибка: Нет такого города в списке!");
                            }
                            else {
                                System.out.println(cities.find(cityName));
                            }
                            break;
                        case("Информация_по_температуре"):
                            if (arguments.isBlank()) {
                                System.out.println("Ошибка: Не указана температура!");
                                break;
                            }
                            int temperature;
                            try {
                                temperature = Integer.parseInt(arguments.split("\\s+")[0]);
                            } catch (NumberFormatException e) {
                                System.out.println("Ошибка: Введено не число!");
                                break;
                            }
                            TreeSet<City> response1 = cities.find(temperature);
                            if (response1.isEmpty()) {
                                System.out.println("Города с указанной температурой не найдены");
                            }
                            else {
                                System.out.println(response1);
                            }
                            break;
                        case("Удалить"):
                            if (arguments.isEmpty()) {
                                System.out.println("Ошибка: Не указаны города!");
                                break;
                            }
                            String[] cityNames1 = arguments.split("\\s+");
                            int countBefore1 = cities.size();
                            cities.delete(cityNames1);
                            System.out.println("Удалено городов: " + (countBefore1 - cities.size()));
                            break;
                        case("Закончить"):
                            isWorking = false;
                            System.out.println("До свидания! Хорошей вам погоды! :) хихик");
                            break;
                        default:
                            System.out.println("Неизвестная команда!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
    @org.jetbrains.annotations.NotNull
    private static String formatCommand(@NotNull String command) {
        return command.substring(0, 1).toUpperCase() + command.substring(1).toLowerCase();
    }
}