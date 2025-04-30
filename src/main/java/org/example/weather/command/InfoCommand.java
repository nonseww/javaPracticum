package org.example.weather.command;

import org.example.weather.command.printer.Printer;
import org.example.weather.domain.City;
import org.example.weather.exception.command.TooManyArgumentsException;
import org.example.weather.printer.CityPrinter;
import org.example.weather.printer.RepositoryStatusPrinter;
import org.example.weather.repository.criteria.*;
import org.example.weather.repository.specification.OrSpecification;
import org.example.weather.service.CityService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class InfoCommand implements Command {
    private final CityService cityService;
    private final CityPrinter cityPrinter;
    private final RepositoryStatusPrinter repositoryStatusPrinter;
    private final Printer printer;

    public InfoCommand(CityService cityService,
                       CityPrinter cityPrinter,
                       RepositoryStatusPrinter repositoryStatusPrinter,
                       Printer printer) {
        this.cityService = cityService;
        this.cityPrinter = cityPrinter;
        this.repositoryStatusPrinter = repositoryStatusPrinter;
        this.printer = printer;
    }

    @Override
    public void execute(String @NotNull [] args) {
        List<City> response;
        if (args.length > 0 && !args[0].isBlank()) { // аргументы есть
            SearchCriteria searchCriteria = null;
            if (Objects.equals(args[0].toLowerCase(), "ровно")
                    || Objects.equals(args[0].toLowerCase(), "выше")
                    || Objects.equals(args[0].toLowerCase(), "ниже")
                    || Objects.equals(args[0].toLowerCase(), "выше/ровно")
                    || Objects.equals(args[0].toLowerCase(), "ниже/ровно")) {
                // поиск по температуре
                if (args.length > 2) // слишком много аргументов
                    throw new TooManyArgumentsException();
                int temp;
                try {
                    temp = Integer.parseInt(args[1]);
                } catch(NumberFormatException e) {
                    throw new NumberFormatException("Температура должна быть числом!");
                }
                switch (args[0].toLowerCase()) {
                    case "ровно" -> searchCriteria = new TemperatureEqualSearch(temp);
                    case "ниже" -> searchCriteria = new TemperatureBelowSearch(temp);
                    case "выше" -> searchCriteria = new TemperatureAboveSearch(temp);
                    case "ниже/ровно" -> {
                        SearchCriteria belowCriteria = new TemperatureBelowSearch(temp);
                        SearchCriteria equalCriteria = new TemperatureEqualSearch(temp);
                        searchCriteria = new OrSpecification(belowCriteria, equalCriteria);
                    }
                    case "выше/ровно" -> {
                        SearchCriteria aboveCriteria = new TemperatureAboveSearch(temp);
                        SearchCriteria equalCriteria = new TemperatureEqualSearch(temp);
                        searchCriteria = new OrSpecification(aboveCriteria, equalCriteria);
                    }
                }
            } else { // это поиск по названию
                for (String arg : args) { // все указанные города
                    if (searchCriteria == null)
                        searchCriteria = new NameSearch(arg);
                    else {
                        SearchCriteria nameCriteria = new NameSearch(arg);
                        searchCriteria = new OrSpecification(searchCriteria, nameCriteria);
                    }
                }
            }
            response = cityService.findCitiesByCriteria(searchCriteria);
        } else { // просто хотят информацию по всем городам
            response = cityService.getAllCities();
        }
        repositoryStatusPrinter.print(response.isEmpty(), response.size());
        cityPrinter.print(response);
        printer.print("Информация о городах была успешно выведена");
    }

    @Override
    public String getName() {
        return "информация";
    }
}
