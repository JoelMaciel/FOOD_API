package com.joel.food.domain.repository;

import com.joel.food.domain.model.Permission;

import java.util.List;

public interface PermissionRepository {

    List<Permission> list();
    Permission find(Long id);
    Permission save(Permission permission);
    void remove(Permission permission);
}
