package org.example.weather.command.utils;

import org.example.weather.app.Main;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CommandFileLoader {
    public String loadCommands(String filename) {
        StringBuilder commands = new StringBuilder();
        InputStream commandsInputStream =
                Main.class.getClassLoader().getResourceAsStream(String.format("data/%s.txt", filename));
        if (commandsInputStream != null) {
            try (Scanner scanner = new Scanner(commandsInputStream, StandardCharsets.UTF_8)) {
                while(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                    commands.append(line.concat("\n"));
                }
            }
        }
        return commands.toString();
    }
}
