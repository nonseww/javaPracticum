package org.example.weather.command;

import org.example.weather.command.printer.Printer;
import org.example.weather.exception.command.TooManyArgumentsException;
import org.example.weather.service.CityService;
import org.jetbrains.annotations.NotNull;

public class ClearRepositoryCommand implements Command {
    private final CityService cityService;
    private final Printer printer;

    public ClearRepositoryCommand(CityService cityService, Printer printer) {
        this.cityService = cityService;
        this.printer = printer;
    }

    @Override
    public void execute(String @NotNull [] args) {
        if (args.length > 0 && !args[0].isBlank())
            throw new TooManyArgumentsException();
        cityService.clear();
        printer.print("Репозиторий городов успешно очищен.");
    }

    @Override
    public String getName() {
        return "очистить";
    }
}
