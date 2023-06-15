package com.joel.food.domain.service;

import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantRegistrationService {

    public static final String MSG_RESTAURANT_NOT_FOUND = "There is no restaurant record with code %d";
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
                .orElseThrow(() -> new EntityNotExistsException(
                        String.format(MSG_RESTAURANT_NOT_FOUND, restaurantId)));
    }

}
