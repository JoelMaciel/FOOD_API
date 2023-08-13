package br.com.joelmaciel.food.domain.exception;

public abstract class EntityNotExistsException extends BusinessException {

    public EntityNotExistsException(String message) {
        super(message);
    }
}
