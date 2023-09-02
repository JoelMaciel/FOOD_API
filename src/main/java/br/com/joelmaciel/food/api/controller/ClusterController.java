package br.com.joelmaciel.food.api.controller;


import br.com.joelmaciel.food.api.dtos.request.ClusterRequest;
import br.com.joelmaciel.food.api.dtos.response.ClusterDTO;
import br.com.joelmaciel.food.domain.service.ClusterRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clusters")
public class ClusterController {

    private final ClusterRegistrationService clusterRegistrationService;

    @GetMapping
    public List<ClusterDTO> findAll() {
        return clusterRegistrationService.findAllCuster();
    }

    @GetMapping("/{clusterId}")
    public ClusterDTO getOneCluster(@PathVariable Long clusterId) {
        return clusterRegistrationService.findByClusterId(clusterId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClusterDTO addCluster(@RequestBody @Valid ClusterRequest clusterRequest) {
        return clusterRegistrationService.saveCluster(clusterRequest);
    }

    @PutMapping("/{clusterId}")
    public ClusterDTO update(@PathVariable Long clusterId, @RequestBody @Valid ClusterRequest clusterRequest) {
        return clusterRegistrationService.updateCluster(clusterId, clusterRequest);
    }

    @DeleteMapping("/{clusterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long clusterId) {
        clusterRegistrationService.remove(clusterId);
    }

}
