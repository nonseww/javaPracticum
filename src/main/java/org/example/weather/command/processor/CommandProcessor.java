package org.example.weather.command.processor;

import org.example.weather.command.Command;
import org.example.weather.command.factory.CommandFactory;
import org.example.weather.exception.command.CommandNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class CommandProcessor {
    private final CommandFactory commandFactory;

    public CommandProcessor(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }

    public void process(@NotNull String fullCommand) throws SQLException {
        fullCommand = fullCommand.toLowerCase();
        String[] parts = fullCommand.split("\\s+", 2);
        String commandName = parts[0];
        String[] arguments = parts.length > 1 ? parts[1].split("\\s+") : new String[]{""};

        Command command = commandFactory.getCommand(commandName);
        if (command == null)
            throw new CommandNotFoundException(commandName);
        command.execute(arguments);
    }
}
