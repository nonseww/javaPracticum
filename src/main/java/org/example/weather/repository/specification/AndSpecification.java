package org.example.weather.repository.specification;

import org.example.weather.domain.City;
import org.example.weather.repository.criteria.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

public class AndSpecification implements SearchCriteria<City> {
    private final SearchCriteria<City> left;
    private final SearchCriteria<City> right;

    public AndSpecification(SearchCriteria<City> left, SearchCriteria<City> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean test(City city) {
        return left.test(city) && right.test(city);
    }


    @Override
    public String toSqlClause() {
        return "(" + left.toSqlClause() + " AND " + right.toSqlClause() + ")";
    }

    @Override
    public List<Object> getParameters() {
        List<Object> params = new ArrayList<>();
        params.addAll(left.getParameters());
        params.addAll(right.getParameters());
        return params;
    }
}
