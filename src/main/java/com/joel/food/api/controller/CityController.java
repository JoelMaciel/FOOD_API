package com.joel.food.api.controller;

import com.joel.food.domain.exception.BusinessException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.exception.StateNotFoundException;
import com.joel.food.domain.model.City;
import com.joel.food.domain.repository.CityRepository;
import com.joel.food.domain.service.CityRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private final CityRepository cityRepository;
    private final CityRegistrationService cityService;

    @GetMapping
    public List<City> list() {
        return cityRepository.findAll();
    }

    @GetMapping("/{cityId}")
    public City findById(@PathVariable Long cityId) {
        return cityService.searchById(cityId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City add(@RequestBody @Valid City city) {
        try {
            return cityService.save(city);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{cityId}")
    public City update(@PathVariable Long cityId, @RequestBody @Valid City city) {
        City currentCity = cityService.searchById(cityId);

        BeanUtils.copyProperties(city, currentCity, "id");
        try {
            return cityService.save(currentCity);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cityId) {
        cityService.remove(cityId);
    }

}
