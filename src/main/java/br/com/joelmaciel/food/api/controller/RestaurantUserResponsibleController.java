package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.response.UserDTO;
import br.com.joelmaciel.food.domain.service.RestaurantRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/restaurants/{restaurantId}/responsible")
public class RestaurantUserResponsibleController {

    private final RestaurantRegistrationService restaurantService;

    @GetMapping
    public Set<UserDTO> listAll(@PathVariable Long restaurantId) {
        return restaurantService.findAllUsersRestaurants(restaurantId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.associateResponsible(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantService.disassociateResponsible(restaurantId, userId);
    }

}
