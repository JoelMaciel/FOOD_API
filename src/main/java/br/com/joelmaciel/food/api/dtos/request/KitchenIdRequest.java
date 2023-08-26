package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.Kitchen;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class KitchenIdRequest {

    @NotNull
    private Long id;
    private String name;

    public static Kitchen toEntity(KitchenIdRequest kitchenIdRequest) {
        return Kitchen.builder()
                .id(kitchenIdRequest.getId())
                .name(kitchenIdRequest.getName())
                .build();
    }

}
