package com.joel.food.api.controller;

import com.joel.food.domain.exception.EntityInUseException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.State;
import com.joel.food.domain.repository.StateRepository;
import com.joel.food.domain.service.StateRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {

    private final StateRepository stateRepository;
    private final StateRegistrationService stateService;

    @GetMapping
    public List<State> allStates() {
        return stateRepository.list();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> findById(@PathVariable Long stateId) {
        State state = stateRepository.find(stateId);

        if (state != null) {
            return ResponseEntity.ok(state);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public State save(@RequestBody State state) {
        return stateService.save(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> update(@PathVariable Long stateId, @RequestBody State state) {
        State currentState = stateRepository.find(stateId);

        if (currentState != null) {
            BeanUtils.copyProperties(state, currentState, "id");
            stateService.save(currentState);
            return ResponseEntity.ok(currentState);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<?> remove(@PathVariable Long stateId) {
        try {
            stateService.remove(stateId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotExistsException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
