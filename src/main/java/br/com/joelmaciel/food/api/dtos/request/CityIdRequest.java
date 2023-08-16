package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.City;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CityIdRequest {

    @NotNull
    private Long id;

    public static City toEntity(CityIdRequest cityIdRequest) {
        return City.builder()
                .id(cityIdRequest.getId())
                .build();
    }

}
