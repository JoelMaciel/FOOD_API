package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.State;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StateDTO {

    private Long id;
    private String name;

    public static StateDTO toDTO(State state) {
        return StateDTO.builder()
                .id(state.getId())
                .name(state.getName())
                .build();
    }

}
