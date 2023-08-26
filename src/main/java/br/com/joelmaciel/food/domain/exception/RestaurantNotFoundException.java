package br.com.joelmaciel.food.domain.exception;

public class RestaurantNotFoundException extends EntityNotExistException {

    public RestaurantNotFoundException(String message) {
        super(message);
    }

    public RestaurantNotFoundException(Long restauranteId) {
        this(String.format("There is no record of restaurant with code %d", restauranteId));
    }
}
