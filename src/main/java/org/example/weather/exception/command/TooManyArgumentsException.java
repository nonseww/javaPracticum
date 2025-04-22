package org.example.weather.exception.command;

public class TooManyArgumentsException extends RuntimeException {
    public TooManyArgumentsException() {
        super("Команда не принимает аргументов");
    }
}
