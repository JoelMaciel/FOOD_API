package com.joel.food.domain.service;

import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.KitchenRepository;
import com.joel.food.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantRegistrationService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenRepository kitchenRepository;

    public Restaurant save(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchen = kitchenRepository.findById(kitchenId);

        if (kitchen == null) {
            throw new EntityNotExistsException(
                    String.format("There is no kitchen record with the code %d", kitchenId));
        }
        restaurant.setKitchen(kitchen);
        return restaurantRepository.add(restaurant);
    }
}
