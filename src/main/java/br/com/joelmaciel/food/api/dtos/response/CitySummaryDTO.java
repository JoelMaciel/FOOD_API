package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CitySummaryDTO {

    private Long id;
    private String name;
    private String state;

    public static CitySummaryDTO toDTO(City city) {
        return CitySummaryDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .state(city.getState().getName())
                .build();
    }

}
