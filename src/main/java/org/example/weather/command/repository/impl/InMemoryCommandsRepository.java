package org.example.weather.command.repository.impl;

import org.example.weather.command.repository.CommandsRepository;

public class InMemoryCommandsRepository implements CommandsRepository {
    private final String existingCommands;

    public InMemoryCommandsRepository(String existingCommands) {
        this.existingCommands = existingCommands;
    }

    @Override
    public String getCommands() {
        return existingCommands;
    }
}
