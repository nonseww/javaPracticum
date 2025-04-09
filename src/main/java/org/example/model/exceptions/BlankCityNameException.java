package org.example.model.exceptions;

public class BlankCityNameException extends CityValidationException {
  public BlankCityNameException() {
    super("Название города не может быть пустым");
  }
}
