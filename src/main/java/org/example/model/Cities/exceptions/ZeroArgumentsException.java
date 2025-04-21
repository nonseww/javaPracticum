package org.example.model.Cities.exceptions;

public class ZeroArgumentsException extends CitiesValidationException {
    public ZeroArgumentsException() {
        super("Ошибка: Не было передано аргументов!");
    }
}
