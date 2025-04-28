package org.example.weather.printer;

import org.example.weather.formatter.TextRepositoryStatusFormatter;
import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

public class ConsoleRepositoryStatusPrinter implements RepositoryStatusPrinter{
    private final TextRepositoryStatusFormatter textRepositoryStatusFormatter;

    public ConsoleRepositoryStatusPrinter(TextRepositoryStatusFormatter textRepositoryStatusFormatter) {
        this.textRepositoryStatusFormatter = textRepositoryStatusFormatter;
    }

    public void print(boolean isEmpty, int cityCount) { System.out.println(textRepositoryStatusFormatter.formatStatus(isEmpty, cityCount));
    }
}
