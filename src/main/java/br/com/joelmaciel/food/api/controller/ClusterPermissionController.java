package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.response.PermissionDTO;
import br.com.joelmaciel.food.domain.service.PermissionRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/clusters/{clusterId}/permissions")
public class ClusterPermissionController {

    private final PermissionRegistrationService permissionService;

    @GetMapping
    public Set<PermissionDTO> listAll(@PathVariable Long clusterId) {
        return permissionService.findAll(clusterId);
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long clusterId, @PathVariable Long permissionId) {
        permissionService.associatePermission(clusterId, permissionId);
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long clusterId, @PathVariable Long permissionId) {
        permissionService.disassociatePermission(clusterId, permissionId);
    }



}
