package com.joel.food.api.controller;

import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.KitchenRepository;
import com.joel.food.domain.repository.RestaurantRepository;
import com.joel.food.domain.service.RestaurantRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    private final KitchenRepository kitchenRepository;
    private final RestaurantRegistrationService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.allRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?>  save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (EntityNotExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
