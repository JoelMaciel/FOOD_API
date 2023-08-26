package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.Kitchen;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KitchenRequest {

    @NotBlank
    private String name;

    public static Kitchen toEntity(KitchenRequest kitchenIdRequest) {
        return Kitchen.builder()
                .name(kitchenIdRequest.getName())
                .build();
    }

}
