package com.joel.food.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class EntityNotExistsException extends BusinessException {

    public EntityNotExistsException(String message) {
        super(message);
    }
}
