package com.joel.food.domain.service;

import com.joel.food.domain.exception.EntityInUseException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.State;
import com.joel.food.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StateRegistrationService {

    public static final String MSG_STATE_NOT_FOUND = "There is no record of state with code %d";
    public static final String MSG_STATE_IN_USE = "Code state %d cannot be removed as it is in use";
    private final StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void remove(Long stateId) {
        try {
            stateRepository.deleteById(stateId);
        }catch (EmptyResultDataAccessException e) {
            throw new EntityNotExistsException(
                    String.format(MSG_STATE_NOT_FOUND, stateId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_STATE_IN_USE, stateId));
        }
    }

    public State searchById(Long stateId) {
        return stateRepository.findById(stateId).orElseThrow(() -> new EntityNotExistsException(
                String.format(MSG_STATE_NOT_FOUND, stateId)));
    }
}
