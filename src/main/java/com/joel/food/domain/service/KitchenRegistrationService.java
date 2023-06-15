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

    public static final String MSG_KITCHEN_NOT_FOUND = "There is no kitchen record with the code %d";
    public static final String MSG_KITCHEN_IN_USE = "Kitchen code %d cannot be removed as it is in use";
    private final KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void remove(Long kitchenId) {
        try {
            kitchenRepository.deleteById(kitchenId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotExistsException(
                    String.format(MSG_KITCHEN_NOT_FOUND, kitchenId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MSG_KITCHEN_IN_USE, kitchenId));
        }
    }

    public Kitchen searchById(Long kitchenId) {
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotExistsException(
                        String.format(MSG_KITCHEN_NOT_FOUND, kitchenId)));
    }
}
