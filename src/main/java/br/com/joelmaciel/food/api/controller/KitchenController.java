package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.request.KitchenRequest;
import br.com.joelmaciel.food.api.dtos.response.KitchenDTO;
import br.com.joelmaciel.food.domain.repository.KitchenRepository;
import br.com.joelmaciel.food.domain.service.KitchenRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/kitchens")
public class KitchenController {

    private final KitchenRepository kitchenRepository;

    private final KitchenRegistrationService kitchenService;

    @GetMapping
    public List<KitchenDTO> getAll() {
        return kitchenService.findAllKitchens();
    }

    @GetMapping("/{kitchenId}")
    public KitchenDTO findByKitchenId(@PathVariable Long kitchenId) {
        return kitchenService.findById(kitchenId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenDTO add(@RequestBody @Valid KitchenRequest kitchenRequest) {
        return kitchenService.save(kitchenRequest);
    }

    @PutMapping("/{kitchenId}")
    public KitchenDTO update(@PathVariable Long kitchenId, @RequestBody @Valid KitchenRequest kitchenRequest) {
        return kitchenService.updateKitchen(kitchenId, kitchenRequest);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{kitchenId}")
    public void remove(@PathVariable Long kitchenId) {
        kitchenService.remove(kitchenId);
    }
}
