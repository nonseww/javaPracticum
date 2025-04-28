package org.example.weather.command.service;

import org.example.weather.command.repository.CommandsRepository;

public class CommandService {
    private final CommandsRepository commandsRepository;

   public  CommandService(CommandsRepository commandsRepository) {
       this.commandsRepository = commandsRepository;
   }

   public String getCommands() {
       return commandsRepository.getCommands();
   }
}
