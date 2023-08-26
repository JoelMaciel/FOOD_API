package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.Cluster;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class ClusterRequest {

    @NotBlank
    private String name;

    public static Cluster toEntity(ClusterRequest clusterRequest) {
        return Cluster.builder()
                .name(clusterRequest.getName())
                .build();
    }
}
