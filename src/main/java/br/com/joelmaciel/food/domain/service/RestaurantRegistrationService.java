package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.request.AddressRequest;
import br.com.joelmaciel.food.api.dtos.request.KitchenIdRequest;
import br.com.joelmaciel.food.api.dtos.request.RestaurantRequest;
import br.com.joelmaciel.food.api.dtos.response.RestaurantDTO;
import br.com.joelmaciel.food.api.dtos.response.UserDTO;
import br.com.joelmaciel.food.domain.exception.BusinessException;
import br.com.joelmaciel.food.domain.exception.CityNotFoundException;
import br.com.joelmaciel.food.domain.exception.KitchenNotFoundException;
import br.com.joelmaciel.food.domain.exception.RestaurantNotFoundException;
import br.com.joelmaciel.food.domain.model.City;
import br.com.joelmaciel.food.domain.model.Kitchen;
import br.com.joelmaciel.food.domain.model.Restaurant;
import br.com.joelmaciel.food.domain.model.User;
import br.com.joelmaciel.food.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantRegistrationService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenRegistrationService kitchenService;
    private final CityRegistrationService cityRegistrationService;
    private final UserRegistrationService userService;


    public List<RestaurantDTO> findAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(RestaurantDTO::toDTO)
                .toList();
    }

    public Set<UserDTO> findAllUsersRestaurants(Long restaurantId) {
        Restaurant restaurant = searchById(restaurantId);
        return restaurant.getResponsible().stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toSet());
    }


    public RestaurantDTO findById(Long restaurantId) {
        Restaurant restaurant = searchById(restaurantId);
        return RestaurantDTO.toDTO(restaurant);
    }

    @Transactional
    public RestaurantDTO save(RestaurantRequest restaurantRequest) {
        try {
            City city = getCityFromRequest(restaurantRequest);
            Kitchen kitchen = getKitchenFromRequest(restaurantRequest);

            Restaurant restaurant = createRestaurantFromRequest(restaurantRequest, city, kitchen);

            return RestaurantDTO.toDTO(restaurantRepository.save(restaurant));
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private City getCityFromRequest(RestaurantRequest restaurantRequest) {
        Long cityId = restaurantRequest.getAddress().getCity().getId();
        return cityRegistrationService.searchById(cityId);
    }

    private Kitchen getKitchenFromRequest(RestaurantRequest restaurantRequest) {
        Long kitchenId = restaurantRequest.getKitchen().getId();
        Kitchen kitchen = kitchenService.searchById(kitchenId);
        restaurantRequest.getKitchen().setName(kitchen.getName());
        return kitchen;
    }

    private Restaurant createRestaurantFromRequest(
            RestaurantRequest restaurantRequest, City city, Kitchen kitchen) {
        Restaurant restaurant = RestaurantRequest.toModel(restaurantRequest);
        restaurant.getAddress().getCity().setState(city.getState());
        restaurant.getAddress().setCity(city);
        restaurant.getKitchen().setName(kitchen.getName());
        return restaurant;
    }


    @Transactional
    public RestaurantDTO update(Long restaurantId, RestaurantRequest restaurantRequest) {

        Restaurant restaurant = searchById(restaurantId).toBuilder()
                .name(restaurantRequest.getName())
                .freightRate(restaurantRequest.getFreightRate())
                .kitchen(KitchenIdRequest.toEntity(restaurantRequest.getKitchen()))
                .address(AddressRequest.toEntity(restaurantRequest.getAddress()))
                .active(restaurantRequest.getActive())
                .build();
        try {
            validateCity(restaurantRequest);
            kitchenService.searchById(restaurantRequest.getKitchen().getId());
            return RestaurantDTO.toDTO(restaurantRepository.save(restaurant));
        } catch (KitchenNotFoundException | CityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public void activate(Long restaurantId) {
        Restaurant restaurant = searchById(restaurantId);
        restaurant.activate();
    }

    @Transactional
    public void inactivate(Long restaurantId) {
        Restaurant restaurant = searchById(restaurantId);
        restaurant.inactivate();
    }

    @Transactional
    public void activate(List<Long> restaurantIds) {
        try {
            restaurantIds.forEach(this::activate);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void inactivate(List<Long> restaurantIds) {
        try {
            restaurantIds.forEach(this::inactivate);
        } catch (RestaurantNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void open(Long restaurantId) {
        Restaurant restaurant = searchById(restaurantId);
        restaurant.open();
    }

    @Transactional
    public void close(Long restaurantId) {
        Restaurant restaurant = searchById(restaurantId);
        restaurant.close();
    }

    @Transactional
    public void associateResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = searchById(restaurantId);
        User user = userService.searchByUserId(userId);
        restaurant.addResponsible(user);
    }

    @Transactional
    public void disassociateResponsible(Long restaurantId, Long userId) {
        Restaurant restaurant = searchById(restaurantId);
        User user = userService.searchByUserId(userId);
        restaurant.removeResponsible(user);
    }


    private void validateCity(RestaurantRequest restaurantRequest) {
        Long cityId = restaurantRequest.getAddress().getCity().getId();
        cityRegistrationService.searchById(cityId);
    }


    public Restaurant searchById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }


}
