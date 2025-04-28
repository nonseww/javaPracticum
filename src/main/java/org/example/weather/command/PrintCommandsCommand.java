package org.example.weather.command;

import org.example.weather.command.printer.Printer;
import org.example.weather.command.service.CommandService;
import org.example.weather.exception.command.TooManyArgumentsException;

public class PrintCommandsCommand implements Command {
    private final CommandService commandService;
    private final Printer printer;

    public PrintCommandsCommand(CommandService commandService, Printer printer) {
        this.commandService = commandService;
        this.printer = printer;
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0 && !args[0].isBlank())
            throw new TooManyArgumentsException();
        printer.print(commandService.getCommands());
    }

    @Override
    public String getName() {
        return "команды";
    }
}
