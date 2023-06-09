package com.joel.food.api.controller;

import com.joel.food.domain.exception.EntityInUseException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.City;
import com.joel.food.domain.repository.CityRepository;
import com.joel.food.domain.service.CityRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<City> findById(@PathVariable Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);

        if (city.isPresent()) {
            return ResponseEntity.ok(city.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody City city) {
        try {
            city = cityService.save(city);
            return ResponseEntity.status(HttpStatus.CREATED).body(city);
        } catch (EntityNotExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
        try {
            City currentCity = cityRepository.findById(cityId).orElseThrow(() -> new EntityNotExistsException(
                    String.format("There is no record of state with code %d", cityId)));

            if (currentCity != null) {
                BeanUtils.copyProperties(city, currentCity, "id");
                currentCity = cityService.save(currentCity);
                return ResponseEntity.ok(currentCity);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> remove(@PathVariable Long cityId) {
        try {
            cityService.remove(cityId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotExistsException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
