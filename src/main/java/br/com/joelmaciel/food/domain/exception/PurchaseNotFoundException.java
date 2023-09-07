package br.com.joelmaciel.food.domain.exception;

public class PurchaseNotFoundException extends EntityNotExistException {

    public PurchaseNotFoundException(String message) {
        super(message);
    }

    public PurchaseNotFoundException(Long purchaseId) {
        this(String.format("There is no record of purchase with code %d", purchaseId));
    }
}
