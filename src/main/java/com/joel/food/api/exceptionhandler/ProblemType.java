package com.joel.food.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://food_api.com.br" + path;
        this.title = title;
    }
}
