package br.com.joelmaciel.food.domain.exception;

public class UserNotFoundException extends EntityNotExistException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Long userId) {
        this(String.format("There is no record of user with code %d", userId));
    }
}
