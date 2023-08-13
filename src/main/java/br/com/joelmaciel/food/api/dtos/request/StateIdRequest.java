package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.State;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class StateIdRequest {

    @NotNull
    private Long id;

    private String name;

    public static State toModel(StateIdRequest stateIdRequest) {
        return State.builder()
                .id(stateIdRequest.getId())
                .name(stateIdRequest.getName())
                .build();
    }
}
