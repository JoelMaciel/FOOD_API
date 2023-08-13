package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.request.StateRequest;
import br.com.joelmaciel.food.api.dtos.response.StateDTO;
import br.com.joelmaciel.food.domain.model.State;
import br.com.joelmaciel.food.domain.repository.StateRepository;
import br.com.joelmaciel.food.domain.exception.StateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StateRegistrationService {
    public static final String MSG_STATE_IN_USE = "Code state %d cannot be removed as it is in use";
    private final StateRepository stateRepository;

    public List<StateDTO> findAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(StateDTO::toDTO)
                .toList();
    }

    public StateDTO findByStateId(Long stateId) {
        return StateDTO.toDTO(searchById(stateId));
    }

    @Transactional
    public StateDTO save(StateRequest stateRequest) {
        State state = StateRequest.toModel(stateRequest);
        return StateDTO.toDTO(stateRepository.save(state));
    }

    @Transactional
    public StateDTO updateState(Long stateId, StateRequest stateRequest) {
        State state = searchById(stateId).toBuilder()
                .name(stateRequest.getName())
                .build();
        return StateDTO.toDTO(stateRepository.save(state));
    }

    @Transactional
    public void remove(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
            stateRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new StateNotFoundException(stateId);
        } catch (DataIntegrityViolationException e) {
            throw new StateNotFoundException(
                    String.format(MSG_STATE_IN_USE, stateId));
        }
    }

    public State searchById(Long stateId) {
        return stateRepository.findById(stateId)
                .orElseThrow(() -> new StateNotFoundException(stateId));
    }

}
