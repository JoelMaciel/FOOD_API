package br.com.joelmaciel.food.domain.exception;

public class KitchenNotFoundException extends EntityNotExistsException {

    public KitchenNotFoundException(String message) {
        super(message);
    }

    public KitchenNotFoundException(Long kitchenId) {
        this(String.format("There is no record of kitchen with code %d", kitchenId));
    }
}
