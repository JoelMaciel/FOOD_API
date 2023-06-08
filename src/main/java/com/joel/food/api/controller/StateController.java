package com.joel.food.api.controller;

import com.joel.food.domain.model.State;
import com.joel.food.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {

    private final StateRepository stateRepository;

    @GetMapping
    public List<State> allStates() {
        return  stateRepository.list();
    }

}
