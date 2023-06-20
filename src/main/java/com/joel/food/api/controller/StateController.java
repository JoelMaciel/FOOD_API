package com.joel.food.api.controller;

import com.joel.food.domain.model.State;
import com.joel.food.domain.repository.StateRepository;
import com.joel.food.domain.service.StateRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {

    private final StateRepository stateRepository;
    private final StateRegistrationService stateService;

    @GetMapping
    public List<State> allStates() {
        return stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public State findById(@PathVariable Long stateId) {
        return stateService.searchById(stateId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State save(@RequestBody @Valid State state) {
        return stateService.save(state);
    }

    @PutMapping("/{stateId}")
    public State update(@PathVariable Long stateId, @RequestBody @Valid State state) {
        State currentState = stateService.searchById(stateId);

        BeanUtils.copyProperties(state, currentState, "id");
        return stateService.save(currentState);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{stateId}")
    public void remove(@PathVariable Long stateId) {
        stateService.remove(stateId);
    }

}
