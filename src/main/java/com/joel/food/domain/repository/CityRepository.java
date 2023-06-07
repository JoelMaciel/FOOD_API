package com.joel.food.domain.repository;

import com.joel.food.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> list();
    City find(Long id);
    City save(City city);
    void remove(City city);
}
