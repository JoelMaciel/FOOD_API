package com.joel.food.api.controller;

import com.joel.food.domain.exception.EntityInUseException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;
import com.joel.food.domain.service.KitchenRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController {

    private final KitchenRepository kitchenRepository;

    private final KitchenRegistrationService kitchenService;

    @GetMapping
    public List<Kitchen> list() {
        return kitchenRepository.findAll();
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> find(@PathVariable Long kitchenId) {
        Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);

        if (kitchen.isPresent()) {
            return ResponseEntity.ok(kitchen.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
        Optional<Kitchen> currentKitchen = kitchenRepository.findById(kitchenId);

        if (currentKitchen.isPresent()) {
            BeanUtils.copyProperties(kitchen, currentKitchen.get(), "id");
            return ResponseEntity.ok(kitchenService.save(currentKitchen.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> remove(@PathVariable Long kitchenId) {
        try {
            kitchenService.remove(kitchenId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotExistsException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}
