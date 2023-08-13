package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.request.StateRequest;
import br.com.joelmaciel.food.api.dtos.response.StateDTO;
import br.com.joelmaciel.food.domain.service.StateRegistrationService;
import br.com.joelmaciel.food.domain.model.State;
import br.com.joelmaciel.food.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/states")
public class StateController {

    private final StateRepository stateRepository;
    private final StateRegistrationService stateService;

    @GetMapping
    public List<StateDTO> allStates() {
        return stateService.findAllStates();
    }

    @GetMapping("/{stateId}")
    public StateDTO findByStateId(@PathVariable Long stateId) {
        return stateService.findByStateId(stateId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateDTO save(@RequestBody @Valid StateRequest stateRequest) {
        return stateService.save(stateRequest);
    }

    @PutMapping("/{stateId}")
    public StateDTO update(@PathVariable Long stateId, @RequestBody @Valid StateRequest stateRequest) {
        return stateService.updateState(stateId, stateRequest);
    }

//    @PutMapping("/{stateId}")
//    public State update(@PathVariable Long stateId, @RequestBody @Valid State state) {
//        State currentState = stateService.searchById(stateId);
//
//        BeanUtils.copyProperties(state, currentState, "id");
//        return stateService.save(currentState);
//    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{stateId}")
    public void remove(@PathVariable Long stateId) {
        stateService.remove(stateId);
    }

}
