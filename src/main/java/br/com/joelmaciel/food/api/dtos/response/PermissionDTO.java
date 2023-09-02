package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.Permission;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PermissionDTO {

    private Long id;
    private String name;
    private String description;

    public static PermissionDTO toDTO(Permission permission) {
        return PermissionDTO.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
