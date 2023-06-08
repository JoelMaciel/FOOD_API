package com.joel.food.domain.repository;

import java.util.List;

import com.joel.food.domain.model.Kitchen;

public interface KitchenRepository {
	
	List<Kitchen> allKitchens();
	Kitchen findById(Long id);
	Kitchen save(Kitchen kitchen);
	void remove(Long id);

}
