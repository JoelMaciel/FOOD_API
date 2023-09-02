package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.response.PermissionDTO;
import br.com.joelmaciel.food.domain.exception.PermissionNotFoundException;
import br.com.joelmaciel.food.domain.model.Cluster;
import br.com.joelmaciel.food.domain.model.Permission;
import br.com.joelmaciel.food.domain.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionRegistrationService {

    private final PermissionRepository permissionRepository;

    @Lazy
    private final ClusterRegistrationService clusterService;

    public Set<PermissionDTO> findAll(Long clusterId) {
        Cluster cluster = clusterService.searchById(clusterId);
        Set<Permission> permissions = cluster.getPermissions();
        return permissions.stream()
                .map(PermissionDTO::toDTO)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void associatePermission(Long clusterId, Long permissionId) {
        Cluster cluster = clusterService.searchById(clusterId);
        Permission permission = searchById(permissionId);
        cluster.addPermission(permission);
    }

    @Transactional
    public void disassociatePermission(Long clusterId, Long permissionId) {
        Cluster cluster = clusterService.searchById(clusterId);
        Permission permission = searchById(permissionId);
        cluster.removePermission(permission);
    }

    public Permission searchById(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new PermissionNotFoundException(permissionId));
    }


}
