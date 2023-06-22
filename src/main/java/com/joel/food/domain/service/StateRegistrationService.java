package com.joel.food.domain.service;

import com.joel.food.domain.exception.StateNotFoundException;
import com.joel.food.domain.model.State;
import com.joel.food.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StateRegistrationService {
    public static final String MSG_STATE_IN_USE = "Code state %d cannot be removed as it is in use";
    private final StateRepository stateRepository;
    @Transactional
    public State save(State state) {
        return stateRepository.save(state);
    }

    @Transactional
    public void remove(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
        }catch (EmptyResultDataAccessException e) {
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
