package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class RestaurantDTO {

    private Long id;
    private String name;
    private BigDecimal freightRate;
    private KitchenDTO kitchen;
    private Boolean active;
    private Boolean open;
    private AddressDTO address;

    public static RestaurantDTO toDTO(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .freightRate(restaurant.getFreightRate())
                .kitchen(KitchenDTO.toDTO(restaurant.getKitchen()))
                .active(restaurant.getActive())
                .open(restaurant.getOpen())
                .address(AddressDTO.toDTO(restaurant.getAddress()))
                .build();
    }
}
