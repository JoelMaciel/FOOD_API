package com.joel.food.api.controller;

import com.joel.food.domain.exception.BusinessException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.RestaurantRepository;
import com.joel.food.domain.service.RestaurantRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Restaurant findById(@PathVariable Long restaurantId) {
        return restaurantService.searchById(restaurantId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant save(@RequestBody @Valid Restaurant restaurant) {
        try {
            return restaurantService.save(restaurant);
        } catch (EntityNotExistsException e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @PutMapping("/{restaurantId}")
    public Restaurant update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
        Restaurant currentRestaurant = restaurantService.searchById(restaurantId);

        BeanUtils.copyProperties(restaurant, currentRestaurant,
                "id", "formPayments", "address", "creationDate", "products");
        try {
            return restaurantService.save(currentRestaurant);
        } catch (EntityNotExistsException e) {
            throw new BusinessException(e.getMessage());
        }

    }
}
