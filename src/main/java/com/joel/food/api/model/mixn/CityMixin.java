package com.joel.food.api.model.mixn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joel.food.domain.model.State;

public class CityMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;
}
