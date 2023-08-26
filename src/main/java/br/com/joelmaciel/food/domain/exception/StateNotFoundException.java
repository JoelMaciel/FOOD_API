package br.com.joelmaciel.food.domain.exception;

public class StateNotFoundException extends EntityNotExistException {

    public StateNotFoundException(String message) {
        super(message);
    }

    public StateNotFoundException(Long stateId) {
        this(String.format("There is no record of state with code %d", stateId));
    }
}
