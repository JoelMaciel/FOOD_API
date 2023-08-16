package br.com.joelmaciel.food.domain.exception;

public abstract class EntityNotExistException extends BusinessException {

    public EntityNotExistException(String message) {
        super(message);
    }
}
