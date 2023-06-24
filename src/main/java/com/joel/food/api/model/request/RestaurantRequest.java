package com.joel.food.api.model.request;

import com.joel.food.domain.model.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class RestaurantRequest {

    @NotBlank
    private String name;

    @PositiveOrZero
    @NotNull
    private BigDecimal freightRate;

    @Valid
    @NotNull
    private KitchenIdRequest kitchen;

    public static Restaurant toModel(RestaurantRequest restaurantRequest) {
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .freightRate(restaurantRequest.getFreightRate())
                .kitchen(KitchenIdRequest.toModel(restaurantRequest.getKitchen()))
                .build();

    }

}
