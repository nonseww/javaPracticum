package org.example.weather.repository.criteria;

import java.util.List;

public interface SearchCriteria<T> {
    boolean test(T item);

    String toSqlClause();
    List<Object> getParameters();
}
