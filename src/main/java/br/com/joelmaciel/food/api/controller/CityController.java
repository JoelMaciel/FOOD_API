package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.request.CityRequest;
import br.com.joelmaciel.food.api.dtos.response.CityDTO;
import br.com.joelmaciel.food.domain.repository.CityRepository;
import br.com.joelmaciel.food.domain.service.CityRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

    private final CityRepository cityRepository;
    private final CityRegistrationService cityService;

    @GetMapping
    public List<CityDTO> getAll() {
        return cityService.getALlCities();
    }

    @GetMapping("/{cityId}")
    public CityDTO findByCityId(@PathVariable Long cityId) {
        return cityService.findById(cityId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO add(@RequestBody @Valid CityRequest cityRequest) {
        return cityService.save(cityRequest);
    }

    @PutMapping("/{cityId}")
    public CityDTO update(@PathVariable Long cityId, @RequestBody @Valid CityRequest cityRequest) {
        return cityService.updateCity(cityId, cityRequest);
    }


    @DeleteMapping("/{cityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long cityId) {
        cityService.remove(cityId);
    }

}
