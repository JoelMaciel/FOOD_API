package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CityDTO {

    private Long id;
    private String name;
    private StateDTO state;

    public static CityDTO toDTO(City city) {
        return CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .state(StateDTO.toDTO(city.getState()))
                .build();
    }
}
