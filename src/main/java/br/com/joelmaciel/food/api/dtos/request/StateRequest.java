package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.State;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StateRequest {

    @NotBlank
    private String name;

    public static State toModel(StateRequest stateRequest) {
        return State.builder()
                .name(stateRequest.getName())
                .build();
    }
}
