package org.example.weather.command;

import org.example.weather.command.printer.Printer;
import org.example.weather.exception.command.TooManyArgumentsException;
import org.jetbrains.annotations.NotNull;

public class TerminateCommand implements Command {
    private final Printer printer;

    public TerminateCommand(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void execute(String @NotNull [] args) {
        if (args.length > 0 && !args[0].isBlank())
            throw new TooManyArgumentsException();
        printer.print("До свидания! Хорошей погоды вам :)");

    }

    @Override
    public String getName() {
        return "закончить";
    }
}
