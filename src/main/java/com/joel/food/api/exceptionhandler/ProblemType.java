package com.joel.food.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    SYSTEM_ERROR("/system-error", "System-error"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    MESSAGE_INCOMPREHESIBLE("/message-incomprehensible", "Message incomprehensible"),
    RESOURCE_NOT_FUND("/resource-not-found", "Resource not found"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    BUSINESS_ERROR("/business-error", "Business error");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://food_api.com.br" + path;
        this.title = title;
    }
}
