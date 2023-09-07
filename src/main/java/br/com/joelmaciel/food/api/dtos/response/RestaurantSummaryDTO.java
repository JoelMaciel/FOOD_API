package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RestaurantSummaryDTO {

    private Long id;
    private String name;


    public static RestaurantSummaryDTO toDTO(Restaurant restaurant) {
        return RestaurantSummaryDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .build();
    }
}
