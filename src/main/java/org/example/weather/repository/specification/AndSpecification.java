package org.example.weather.repository.specification;

import org.example.weather.domain.City;
import org.example.weather.repository.criteria.SearchCriteria;

public class AndSpecification implements SearchCriteria {
    private final SearchCriteria left;
    private final SearchCriteria right;

    public AndSpecification(SearchCriteria left, SearchCriteria right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean test(City city) {
        return left.test(city) && right.test(city);
    }
}
