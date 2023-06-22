package com.joel.food.api.model.mixn;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joel.food.domain.model.Restaurant;

import java.util.List;

public abstract class KitchenMixin {

    @JsonIgnore
    private List<Restaurant> restaurants ;
}
