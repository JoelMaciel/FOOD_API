package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.response.ClusterDTO;
import br.com.joelmaciel.food.domain.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/{userId}/clusters")
public class UserClusterController {

    private final UserRegistrationService userService;

    @GetMapping
    public Set<ClusterDTO> list(@PathVariable Long userId) {
        return userService.findAllClusters(userId);

    }

    @PutMapping("/{clusterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long userId, @PathVariable Long clusterId) {
        userService.associateCluster(userId, clusterId);
    }

    @DeleteMapping("/{clusterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long userId, @PathVariable Long clusterId) {
        userService.disassociateCluster(userId, clusterId);
    }
}
