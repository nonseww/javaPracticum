package org.example.weather.exception.city;

public class BlankCityNameException extends CityValidationException {
  public BlankCityNameException() {
    super("Название города не может быть пустым!");
  }
}
