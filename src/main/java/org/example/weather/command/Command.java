package org.example.weather.command;

public interface Command {
    void execute(String[] args);
    String getName();
}
