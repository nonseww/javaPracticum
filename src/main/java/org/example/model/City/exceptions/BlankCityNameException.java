package org.example.model.City.exceptions;

public class BlankCityNameException extends CityValidationException {
  public BlankCityNameException() {
    super("Название города не может быть пустым");
  }
}
