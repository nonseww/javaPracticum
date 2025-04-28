package org.example.weather.utils;

import org.example.weather.exception.jdbc.InitialException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()){
            Statement statement = connection.createStatement();

            InputStream sqlInitial = DatabaseInitializer.class
                    .getClassLoader()
                    .getResourceAsStream("data/sql/schema.sql");

            if (sqlInitial == null) {
                throw new InitialException("SQL-скрип не найден");
            }
            String sql = new String(sqlInitial.readAllBytes(), StandardCharsets.UTF_8);
            statement.execute(sql);
        } catch (SQLException | IOException e) {
            throw new InitialException(e.getMessage());
        }
    }
}
