package org.example.weather.exception.command;

public class ZeroArgumentsException extends CommandArgumentException {
    public ZeroArgumentsException() {
        super("Отсутствуют аргументы в команде");
    }
}
