package org.example.weather.formatter;

public class TextRepositoryStatusFormatter implements RepositoryStatusFormatter {
    public String formatStatus(boolean isEmpty, int cityCount) {
        if (isEmpty) {
            return "Репозиторий городов пуст.";
        } else {
            return String.format("В репозитории %d городов.", cityCount);
        }
    }
}
