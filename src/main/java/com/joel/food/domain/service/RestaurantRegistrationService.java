package com.joel.food.domain.service;

import com.joel.food.domain.exception.RestaurantNotFoundException;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantRegistrationService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenRegistrationService kitchenService;

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenService.searchById(kitchenId);

        restaurant.setKitchen(kitchen);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant searchById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

}
