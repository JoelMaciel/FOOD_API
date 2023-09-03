package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.request.ClusterRequest;
import br.com.joelmaciel.food.api.dtos.response.ClusterDTO;
import br.com.joelmaciel.food.domain.exception.ClusterNotFoundException;
import br.com.joelmaciel.food.domain.exception.EntityInUseException;
import br.com.joelmaciel.food.domain.model.Cluster;
import br.com.joelmaciel.food.domain.repository.ClusterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClusterRegistrationService {

    public static final String MSG_CLUSTER_IN_USE = "Code cluster %d cannot be removed as it is in use";

    private final ClusterRepository clusterRepository;

    public List<ClusterDTO> findAllCuster() {
        List<Cluster> clusters = clusterRepository.findAll();
        return clusters.stream()
                .map(ClusterDTO::toDTO)
                .toList();
    }

    public ClusterDTO findByClusterId(Long clusterId) {
        Cluster cluster = searchById(clusterId);
        return ClusterDTO.toDTO(cluster);
    }

    @Transactional
    public ClusterDTO saveCluster(ClusterRequest clusterRequest) {
        Cluster cluster = ClusterRequest.toEntity(clusterRequest);
        return ClusterDTO.toDTO(clusterRepository.save(cluster));
    }

    @Transactional
    public void remove(Long clusterId) {
        try {
            clusterRepository.deleteById(clusterId);
            clusterRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ClusterNotFoundException(clusterId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(MSG_CLUSTER_IN_USE);
        }
    }

    @Transactional
    public ClusterDTO updateCluster(Long clusterId, ClusterRequest clusterRequest) {
        Cluster cluster = searchById(clusterId).toBuilder()
                .name(clusterRequest.getName())
                .build();
        return ClusterDTO.toDTO(clusterRepository.save(cluster));
    }


    public Cluster searchById(Long clusterId) {
        return clusterRepository.findById(clusterId)
                .orElseThrow(() -> new ClusterNotFoundException(clusterId));
    }


}
