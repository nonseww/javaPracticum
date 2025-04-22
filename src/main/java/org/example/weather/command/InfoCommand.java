package org.example.weather.command;

import org.example.weather.command.printer.Printer;
import org.example.weather.domain.City;
import org.example.weather.exception.command.TooManyArgumentsException;
import org.example.weather.printer.CityPrinter;
import org.example.weather.printer.RepositoryStatusPrinter;
import org.example.weather.service.CityService;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;
import java.util.List;

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
        if (args.length > 0 && !args[0].isBlank())
            throw new TooManyArgumentsException();
        List<City> response = cityService.getAllCities();
        repositoryStatusPrinter.print(response.isEmpty(), response.size());
        cityPrinter.print(cityService.getAllCities());
        printer.print("Информация о городах была успешно выведена");
    }

    @Override
    public String getName() {
        return "информация";
    }
}
