package com.joel.food.domain.exception;

public class RestaurantNotFoundException extends EntityNotExistsException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long restauranteId) {
        this(String.format("There is no record of restaurant with code %d", restauranteId));
    }
}
