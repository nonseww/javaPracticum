package org.example.weather.command.printer;

public class ConsolePrinter implements Printer{

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
