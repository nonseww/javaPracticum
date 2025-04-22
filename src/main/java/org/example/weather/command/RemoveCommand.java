package org.example.weather.command;

import org.example.weather.command.printer.Printer;
import org.example.weather.exception.cityRepository.CityNotFoundException;
import org.example.weather.exception.command.ZeroArgumentsException;
import org.example.weather.service.CityService;
import org.jetbrains.annotations.NotNull;

public class RemoveCommand implements Command {
    private final CityService cityService;
    private final Printer printer;

    public RemoveCommand(CityService cityService, Printer printer) {
        this.cityService = cityService;
        this.printer = printer;
    }

    @Override
    public void execute(String @NotNull [] args) {
        if (args.length == 0 || args[0].isBlank())
            throw new ZeroArgumentsException();
        int countBefore = cityService.getCount();
        try {
            cityService.removeCities(args);
        } catch(CityNotFoundException e) {
            throw e;
        } finally {
            printer.print(String.format("Было удалено %d городов!", countBefore - cityService.getCount()));
        }
    }

    @Override
    public String getName() {
        return "удалить";
    }
}
