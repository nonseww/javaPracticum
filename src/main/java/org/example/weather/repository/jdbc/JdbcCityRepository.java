package org.example.weather.repository.jdbc;

import org.example.weather.domain.City;
import org.example.weather.exception.jdbc.DataAccessException;
import org.example.weather.repository.CityRepository;
import org.example.weather.repository.criteria.SearchCriteria;
import org.example.weather.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCityRepository implements CityRepository {
    @Override
    public void add(City city) throws SQLException {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO city(name, temperature) VALUES (?, ?)"
            )) {
                preparedStatement.setString(1, city.getName());
                preparedStatement.setInt(2, city.getTemperature());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void remove(String name) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM city WHERE name=?"
            )) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
            }
        } catch(SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public void clear() {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("TRUNCATE TABLE city RESTART IDENTITY");
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public boolean isEmpty() {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            try (Statement statement = connection.createStatement();
            ) {
                ResultSet resultSet = statement.executeQuery("SELECT id FROM city");
                return resultSet == null;
            }
        } catch(SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public List<City> findByCriteria(SearchCriteria criteria) {
        return List.of();
    }

    @Override
    public List<City> getAllCities() {
        Connection connection = null;
        List<City> result = new ArrayList<>();
        try {
            connection = DatabaseConnection.getConnection();
            try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT name, temperature FROM city")
            ) {
                while (resultSet.next()) {
                    result.add(new City(resultSet.getString("name"),
                            resultSet.getInt("temperature")));
                }
            }
        } catch(SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return result;
    }

    @Override
    public boolean existsByName(String name) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT id FROM city WHERE name=?"
            )) {
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.getResultSet();
                return resultSet != null;
            }
        } catch(SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }
}
