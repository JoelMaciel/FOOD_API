package br.com.joelmaciel.food.domain.exception;

public class ProductNotFoundException extends EntityNotExistException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Long restaurantId, Long productId) {
        this(String.format("There is no restaurant record with code %d for the product with code %d", restaurantId, productId));
    }
}
