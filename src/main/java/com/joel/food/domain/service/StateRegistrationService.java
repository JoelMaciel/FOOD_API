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

    private final StateRepository stateRepository;

    public State save(State state) {
        return stateRepository.save(state);
    }

    public void remove(Long stateId) {
        try {
            stateRepository.remove(stateId);
        }catch (EmptyResultDataAccessException e) {
            throw new EntityNotExistsException(
                    String.format("There is no record of state with code %d", stateId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code state %d cannot be removed as it is in use", stateId));
        }
    }
}
