package br.com.joelmaciel.food.domain.exception;

public class CityNotFoundException extends EntityNotExistsException {

    public CityNotFoundException(String message) {
        super(message);
    }

    public CityNotFoundException(Long cityId) {
        this(String.format("There is no record of city with code %d", cityId));
    }
}
