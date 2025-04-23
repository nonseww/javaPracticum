package org.example.weather.command.factory;

import org.example.weather.command.*;
import org.example.weather.command.printer.Printer;
import org.example.weather.command.service.CommandService;
import org.example.weather.printer.CityPrinter;
import org.example.weather.printer.RepositoryStatusPrinter;
import org.example.weather.service.CityService;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandFactory(CityService cityService,
                          Printer printer,
                          CityPrinter cityPrinter,
                          RepositoryStatusPrinter repositoryStatusPrinter,
                          CommandService commandService) {
        AddCommand addCommand = new AddCommand(cityService, printer);
        RemoveCommand removeCommand = new RemoveCommand(cityService, printer);
        InfoCommand infoCommand = new InfoCommand(cityService, cityPrinter, repositoryStatusPrinter, printer);
        ClearRepositoryCommand clearRepositoryCommand = new ClearRepositoryCommand(cityService, printer);
        TerminateCommand terminateCommand = new TerminateCommand(printer);
        PrintCommandsCommand printCommandsCommand = new PrintCommandsCommand(commandService, printer);

        commands.put(addCommand.getName(), addCommand);
        commands.put(removeCommand.getName(), removeCommand);
        commands.put(infoCommand.getName(), infoCommand);
        commands.put(clearRepositoryCommand.getName(), clearRepositoryCommand);
        commands.put(terminateCommand.getName(), terminateCommand);
        commands.put(printCommandsCommand.getName(), printCommandsCommand);
    }

    public Command getCommand(@NotNull String input) {
        return commands.get(input.toLowerCase());
    }

    public String getAvailableCommands() {
        return commands.keySet().stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining("\n"));
    }
}
