package br.com.joelmaciel.food.domain.exception;

public class EmailAlreadyExistsException extends BusinessException{

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
