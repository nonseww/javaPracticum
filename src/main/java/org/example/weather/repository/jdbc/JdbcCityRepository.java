package org.example.weather.repository.jdbc;

import org.example.weather.domain.City;
import org.example.weather.exception.jdbc.DataAccessException;
import org.example.weather.exception.jdbc.DuplicateCityException;
import org.example.weather.repository.CityRepository;
import org.example.weather.repository.criteria.SearchCriteria;
import org.example.weather.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        }
        catch (SQLException e) {
            if ("23505".equals(e.getSQLState()))
                throw new DuplicateCityException(city.getName());
            else
                throw new DataAccessException("Ошибка добавления города, " + e.getMessage());
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
                    "DELETE FROM city WHERE LOWER(name)=?"
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
        ResultSet resultSet;
        try {
            connection = DatabaseConnection.getConnection();
            try (Statement statement = connection.createStatement();
            ) {
                resultSet = statement.executeQuery("SELECT id FROM city");
            }
        } catch(SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return resultSet == null;
    }

    @Override
    public int count() throws SQLException {
        Connection connection = null;
        ResultSet resultSet;
        int count = 0;
        try {
            connection = DatabaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT COUNT(*) AS total FROM city"
            )) {
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    count =  resultSet.getInt("total");
                }
            }
        }  catch (SQLException e) {
            throw new DataAccessException("Ошибка получения количества записей, " + e.getMessage());
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return count;
    }

    @Override
    public List<City> findByCriteria(SearchCriteria<City> criteria) {
        List<City> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT name, temperature FROM city WHERE " + criteria.toSqlClause()
            )) {
                List<Object> params = criteria.getParameters();
                for (int i = 0; i < params.size(); ++i) {
                    preparedStatement.setObject(i + 1, params.get(i));
                }

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    result.add(new City(
                            resultSet.getString("name"),
                            resultSet.getInt("temperature")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return result;
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
        ResultSet resultSet;
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                 "SELECT id FROM city WHERE name=?"
            )) {
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.getResultSet();
            }
        } catch(SQLException e) {
            throw new DataAccessException(e.getMessage());
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return resultSet != null;
    }
}
