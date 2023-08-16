package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.Restaurant;
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
@Builder(toBuilder = true)
public class RestaurantRequest {

    @NotBlank
    private String name;

    @PositiveOrZero
    @NotNull
    private BigDecimal freightRate;

    @Valid
    @NotNull
    private KitchenIdRequest kitchen;

    @NotNull
    private Boolean active;

    @Valid
    @NotNull
    private AddressRequest address;

    public static Restaurant toModel(RestaurantRequest restaurantRequest) {
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .freightRate(restaurantRequest.getFreightRate())
                .kitchen(KitchenIdRequest.toEntity(restaurantRequest.getKitchen()))
                .active(restaurantRequest.getActive())
                .address(AddressRequest.toEntity(restaurantRequest.getAddress()))
                .build();
    }

}
