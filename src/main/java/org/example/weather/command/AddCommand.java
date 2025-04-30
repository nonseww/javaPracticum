package org.example.weather.command;

import org.example.weather.command.printer.Printer;
import org.example.weather.exception.cityRepository.DuplicateCityException;
import org.example.weather.exception.command.ZeroArgumentsException;
import org.example.weather.service.CityService;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class AddCommand implements Command {
    private final CityService cityService;
    private final Printer printer;

    public AddCommand(CityService cityService, Printer printer) {
        this.cityService = cityService;
        this.printer = printer;
    }

    @Override
    public void execute(String @NotNull [] args) throws SQLException {
        if (args.length == 0 || args[0].isBlank())
            throw new ZeroArgumentsException();
        int countBefore = cityService.getCount();
        try {
            cityService.addCities(args);
        } catch (DuplicateCityException e) {
            throw e;
        } finally {
            printer.print(String.format("Было добавлено %d городов!", cityService.getCount() - countBefore));
        }
    }

    @Override
    public String getName() {
        return "добавить";
    }
}
