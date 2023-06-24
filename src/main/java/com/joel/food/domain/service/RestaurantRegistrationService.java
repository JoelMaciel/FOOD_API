package com.joel.food.domain.service;

import com.joel.food.api.model.RestaurantDTO;
import com.joel.food.api.model.request.KitchenIdRequest;
import com.joel.food.api.model.request.RestaurantRequest;
import com.joel.food.domain.exception.BusinessException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.exception.RestaurantNotFoundException;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantRegistrationService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenRegistrationService kitchenService;

    public List<RestaurantDTO> findAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(restaurant -> RestaurantDTO.toDTO(restaurant))
                .collect(Collectors.toList());

    }

    public RestaurantDTO findById(Long restaurantId) {
        Restaurant restaurant = searchById(restaurantId);
        return RestaurantDTO.toDTO(restaurant);
    }


    @Transactional
    public RestaurantDTO update(Long restaurantId, RestaurantRequest restaurantRequest) {

        Restaurant restaurant = searchById(restaurantId).toBuilder()
                .name(restaurantRequest.getName())
                .freightRate(restaurantRequest.getFreightRate())
                .kitchen(KitchenIdRequest.toModel(restaurantRequest.getKitchen()))
                .build();
        try {
            kitchenService.searchById(restaurantRequest.getKitchen().getId());
            return RestaurantDTO.toDTO(restaurantRepository.save(restaurant));
        } catch (EntityNotExistsException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public RestaurantDTO save(RestaurantRequest restaurantRequest) {
        try {
            Kitchen kitchen = kitchenService.searchById(restaurantRequest.getKitchen().getId());
            restaurantRequest.getKitchen().setName(kitchen.getName());
            Restaurant restaurant = restaurantRepository.save(RestaurantRequest.toModel(restaurantRequest));

            return RestaurantDTO.toDTO(restaurant);
        } catch (EntityNotExistsException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    public Restaurant searchById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }


}
