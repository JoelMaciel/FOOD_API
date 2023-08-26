package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserWithPasswordRequest extends UserRequest{

    @NotBlank
    private String password;

    public static User toEntity(UserWithPasswordRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}
