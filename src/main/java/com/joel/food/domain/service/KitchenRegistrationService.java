package com.joel.food.domain.service;

import com.joel.food.domain.exception.EntityInUseException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenRegistrationService {

    private final KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void remove(Long kitchenId) {
        try {
            kitchenRepository.deleteById(kitchenId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotExistsException(
                    String.format("There is no kitchen record with the code %d", kitchenId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Kitchen code %d cannot be removed as it is in use", kitchenId));
        }
    }
}
