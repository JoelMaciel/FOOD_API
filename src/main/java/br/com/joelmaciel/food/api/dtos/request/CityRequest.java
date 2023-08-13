package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.City;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CityRequest {

    @NotBlank
    private String name;

    @Valid
    @NotNull
    private StateIdRequest state;

    public static City toEntity(CityRequest cityRequest) {
        return City.builder()
                .name(cityRequest.getName())
                .state(StateIdRequest.toModel(cityRequest.getState()))
                .build();
    }
}
