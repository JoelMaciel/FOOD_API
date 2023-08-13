package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.domain.service.RestaurantRegistrationService;
import br.com.joelmaciel.food.api.dtos.response.RestaurantDTO;
import br.com.joelmaciel.food.api.dtos.request.RestaurantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantRegistrationService restaurantService;

    @GetMapping
    public List<RestaurantDTO> listAll() {
        return restaurantService.findAll();
    }

    @GetMapping("/{restaurantId}")
    public RestaurantDTO getOne(@PathVariable Long restaurantId) {
        return restaurantService.findById(restaurantId);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantDTO save(@RequestBody @Valid RestaurantRequest restaurantRequest) {
            return restaurantService.save(restaurantRequest);
    }

    @PutMapping("/{restaurantId}")
    public RestaurantDTO update(@PathVariable Long restaurantId, @RequestBody @Valid RestaurantRequest restaurantRequest) {
        return restaurantService.update(restaurantId, restaurantRequest);

    }

}
