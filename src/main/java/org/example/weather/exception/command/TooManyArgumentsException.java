package org.example.weather.exception.command;

public class TooManyArgumentsException extends CommandArgumentException {
    public TooManyArgumentsException() {
        super("Команда не принимает аргументов");
    }
}
