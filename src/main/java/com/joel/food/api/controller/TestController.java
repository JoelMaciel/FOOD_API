package com.joel.food.api.controller;

import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.KitchenRepository;
import com.joel.food.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final KitchenRepository kitchenRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/kitchen/all")
    public List<Kitchen> findByName(@RequestParam String name) {
        return kitchenRepository.findAllByNameContaining(name);
    }

    @GetMapping("/kitchen/by_name")
    public Optional<Kitchen> findOne(@RequestParam String name) {
        return kitchenRepository.findByName(name);
    }

    @GetMapping("/restaurants/rate")
    public List<Restaurant> findByRate(@RequestParam BigDecimal rateInitial, BigDecimal rateFinal) {
        return restaurantRepository.findByFreightRateBetween(rateInitial, rateFinal);
    }

    @GetMapping("/restaurants/by_name_kitchen_id")
    public List<Restaurant> findByRate(String name, Long kitchenId) {
        return restaurantRepository.searchByName(name, kitchenId);
    }

    @GetMapping("/restaurants/first-name")
    public Optional<Restaurant> firstName(String name) {
        return restaurantRepository.findFirstRestaurantByNameContaining(name);
    }

    @GetMapping("/restaurants/to-name")
    public List<Restaurant> toRestaurantsName(String name) {
        return restaurantRepository.findTop2ByNameContaining(name);
    }

    @GetMapping("/restaurants/exists")
    public boolean existsKitchen(String name) {
        return kitchenRepository.existsByName(name);
    }

    @GetMapping("/restaurants/count")
    public int countKitchen(Long kitchenId) {
        return restaurantRepository.countByKitchenId(kitchenId);
    }
}
