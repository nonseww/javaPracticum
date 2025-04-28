package org.example.weather.exception.command;

public class CommandArgumentException extends RuntimeException {
    public CommandArgumentException(String message) {
        super(message);
    }
}
