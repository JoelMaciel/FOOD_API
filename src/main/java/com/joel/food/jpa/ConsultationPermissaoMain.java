package com.joel.food.jpa;


import com.joel.food.FoodApiApplication;
import com.joel.food.domain.model.Permission;
import com.joel.food.domain.repository.PermissionRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;


public class ConsultationPermissaoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissionRepository permissionRepository = applicationContext.getBean(PermissionRepository.class);

        List<Permission> allPermissions = permissionRepository.list();

        for (Permission permission : allPermissions) {
            System.out.printf("%s - %s\n", permission.getName(), permission.getDescription());
        }
    }
}
