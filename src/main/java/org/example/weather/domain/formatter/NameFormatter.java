package org.example.weather.domain.formatter;

import org.jetbrains.annotations.NotNull;

public class NameFormatter {
    public String format(@NotNull String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
