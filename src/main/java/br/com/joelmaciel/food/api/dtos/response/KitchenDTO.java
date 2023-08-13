package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.Kitchen;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KitchenDTO {

    private Long id;
    private String name;

    public static KitchenDTO toDTO(Kitchen kitchen) {
        return KitchenDTO.builder()
                .id(kitchen.getId())
                .name(kitchen.getName())
                .build();
    }
}
