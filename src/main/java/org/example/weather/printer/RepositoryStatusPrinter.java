package org.example.weather.printer;

import org.jetbrains.annotations.NotNull;

import java.io.PrintStream;

public interface RepositoryStatusPrinter {
    public void print(boolean isEmpty, int cityCount);
}
