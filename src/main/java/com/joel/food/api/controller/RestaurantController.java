package com.joel.food.api.controller;

import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.RestaurantRepository;
import com.joel.food.domain.service.RestaurantRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantRegistrationService restaurantService;

    @GetMapping
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);

        if (restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Restaurant restaurant) {
        try {
            restaurant = restaurantService.save(restaurant);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (EntityNotExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        try {
            Restaurant currentRestaurant = restaurantRepository.findById(restaurantId)
                    .orElse(null);

            if (currentRestaurant != null) {
                BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "formPayments", "address");

                currentRestaurant = restaurantService.save(currentRestaurant);
                return ResponseEntity.ok(currentRestaurant);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
