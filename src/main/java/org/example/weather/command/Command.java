package org.example.weather.command;

import java.sql.SQLException;

public interface Command {
    void execute(String[] args) throws SQLException;
    String getName();
}
