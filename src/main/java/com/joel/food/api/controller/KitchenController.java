package com.joel.food.api.controller;

import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;
import com.joel.food.domain.service.KitchenRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Kitchen find(@PathVariable Long kitchenId) {
        return kitchenService.searchById(kitchenId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody @Valid Kitchen kitchen) {
        return kitchenService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public Kitchen update(@PathVariable Long kitchenId, @RequestBody @Valid Kitchen kitchen) {
        var currentKitchen = kitchenService.searchById(kitchenId);

        BeanUtils.copyProperties(kitchen, currentKitchen, "id");
        return kitchenService.save(currentKitchen);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{kitchenId}")
    public void remove(@PathVariable Long kitchenId) {
        kitchenService.remove(kitchenId);
    }
}
