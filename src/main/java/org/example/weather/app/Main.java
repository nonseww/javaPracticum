package org.example.weather.app;

import org.example.weather.command.factory.CommandFactory;
import org.example.weather.command.printer.ConsolePrinter;
import org.example.weather.command.printer.Printer;
import org.example.weather.command.processor.CommandProcessor;
import org.example.weather.command.repository.CommandsRepository;
import org.example.weather.command.repository.impl.InMemoryCommandsRepository;
import org.example.weather.command.service.CommandService;
import org.example.weather.command.utils.CommandFileLoader;
import org.example.weather.domain.factory.CityFactory;
import org.example.weather.domain.formatter.NameFormatter;
import org.example.weather.domain.validator.CityDataValidator;
import org.example.weather.exception.city.InvalidCityNameException;
import org.example.weather.exception.cityRepository.CityRepositoryValidationException;
import org.example.weather.exception.command.CommandArgumentException;
import org.example.weather.exception.command.InvalidCommandException;
import org.example.weather.formatter.CityFormatter;
import org.example.weather.formatter.RepositoryStatusFormatter;
import org.example.weather.formatter.TextCityFormatter;
import org.example.weather.formatter.TextRepositoryStatusFormatter;
import org.example.weather.printer.CityPrinter;
import org.example.weather.printer.ConsoleCityPrinter;
import org.example.weather.printer.ConsoleRepositoryStatusPrinter;
import org.example.weather.printer.RepositoryStatusPrinter;
import org.example.weather.repository.CityRepository;
import org.example.weather.repository.impl.InMemoryCityRepository;
import org.example.weather.repository.jdbc.JdbcCityRepository;
import org.example.weather.service.CityService;
import org.example.weather.service.jdbc.JdbcCityService;
import org.example.weather.utils.DatabaseInitializer;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

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

            try {
                DatabaseInitializer.initialize(); // инициализация БД

                CityDataValidator cityDataValidator = new CityDataValidator();
                NameFormatter nameFormatter = new NameFormatter();
                CityFactory cityFactory = new CityFactory(cityDataValidator, nameFormatter);
                JdbcCityRepository cityRepository = new JdbcCityRepository();
                TextCityFormatter textCityFormatter  = new TextCityFormatter();
                CityPrinter cityPrinter = new ConsoleCityPrinter(textCityFormatter);
                CityService cityService = new JdbcCityService(cityRepository, cityFactory);
                Printer printer = new ConsolePrinter();
                TextRepositoryStatusFormatter repositoryStatusFormatter = new TextRepositoryStatusFormatter();
                RepositoryStatusPrinter repositoryStatusPrinter =
                        new ConsoleRepositoryStatusPrinter(repositoryStatusFormatter);
                CommandFileLoader commandFileLoader = new CommandFileLoader();
                CommandsRepository commandsRepository =
                        new InMemoryCommandsRepository(commandFileLoader.loadCommands("commands"));
                CommandService commandService = new CommandService(commandsRepository);
                CommandFactory commandFactory =
                        new CommandFactory(cityService, printer, cityPrinter, repositoryStatusPrinter, commandService);
                CommandProcessor commandProcessor = new CommandProcessor(commandFactory);

                /*----------------------------*/

                boolean isWorking = true;
                Scanner userScanner = new Scanner(System.in);
                while(isWorking) {
                    try {
                      String fullCommand = userScanner.nextLine().trim();
                      if (!fullCommand.isEmpty()) {
                          commandProcessor.process(fullCommand);
                          if (fullCommand.equalsIgnoreCase("закончить"))
                              isWorking = false;
                      }
                    } catch(InvalidCityNameException e) {
                        System.err.println("Ошибка данных: " + e.getMessage());
                    } catch(CityRepositoryValidationException e) {
                        System.err.println("Ошибка репозитория: " + e.getMessage());
                    } catch(CommandArgumentException e) {
                        System.err.println("Ошибка аргумента в команде: " + e.getMessage());
                    } catch(InvalidCommandException e) {
                        System.err.println("Ошибка команды: " + e.getMessage());
                    } catch(Exception e) {
                        System.err.println("Непредвиденная ошибка: " + e.getMessage());
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