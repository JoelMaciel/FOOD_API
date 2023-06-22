package com.joel.food.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.joel.food.api.model.mixn.CityMixin;
import com.joel.food.api.model.mixn.KitchenMixin;
import com.joel.food.api.model.mixn.RestaurantMixin;
import com.joel.food.domain.model.City;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class JacksonMixinModule extends SimpleModule {

    public JacksonMixinModule() {
        setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
        setMixInAnnotation(City.class, CityMixin.class);
        setMixInAnnotation(Kitchen.class, KitchenMixin.class);
    }
}
