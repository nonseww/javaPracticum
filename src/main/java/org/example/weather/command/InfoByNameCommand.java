package org.example.weather.command;

import org.example.weather.command.printer.Printer;
import org.example.weather.domain.City;
import org.example.weather.exception.command.ZeroArgumentsException;
import org.example.weather.printer.CityPrinter;
import org.example.weather.service.CityService;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class InfoByNameCommand implements Command {
    private final CityService cityService;
    private final CityPrinter cityPrinter;
    private final Printer printer;

    public InfoByNameCommand(CityService cityService, CityPrinter cityPrinter, Printer printer) {
        this.cityService = cityService;
        this.cityPrinter = cityPrinter;
        this.printer = printer;
    }

    @Override
    public void execute(String @NotNull [] args) {
        if (args.length == 0 || args[0].isBlank())
            throw new ZeroArgumentsException();
        Optional<City> response = cityService.findCityByName(args[0]);
        if (response.isEmpty())
            printer.print(String.format("Город %s не был обнаружен в репозитории", args[0]));
        else {
            cityPrinter.print(response.get());
            printer.print("Город был успешно найден");
        }
    }

    @Override
    public String getName() {
        return "информация_город";
    }
}
