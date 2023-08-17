package br.com.joelmaciel.food.api.dtos.response;


import br.com.joelmaciel.food.domain.model.Cluster;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClusterDTO {

    private Long id;
    private String name;

    public static ClusterDTO toDTO(Cluster cluster) {
        return ClusterDTO.builder()
                .id(cluster.getId())
                .name(cluster.getName())
                .build();
    }
}
