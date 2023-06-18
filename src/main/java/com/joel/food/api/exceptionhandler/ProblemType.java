package com.joel.food.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MESSAGE_INCOMPREHESIBLE("/message-incomprehensible", "Message incomprehensible"),
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    BUSINESS_ERROR("/businnes-error", "Business error");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://food_api.com.br" + path;
        this.title = title;
    }
}
