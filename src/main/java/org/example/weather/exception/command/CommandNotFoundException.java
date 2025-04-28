package org.example.weather.exception.command;

public class CommandNotFoundException extends InvalidCommandException {
    public CommandNotFoundException(String command) {
        super("Неизвестная команда: " + command);
    }
}
