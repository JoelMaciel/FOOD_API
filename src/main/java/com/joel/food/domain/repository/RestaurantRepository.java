package com.joel.food.domain.repository;

import java.util.List;

import com.joel.food.domain.model.Restaurant;

public interface RestaurantRepository {

	List<Restaurant> allRestaurants();
	Restaurant findById(Long id);
	Restaurant add(Restaurant restaurant);
	void remove(Restaurant restaurant);

}
