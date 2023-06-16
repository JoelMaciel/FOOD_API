package com.joel.food.domain.service;

import com.joel.food.domain.exception.KitchenNotFoundException;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenRegistrationService {

    public static final String MSG_KITCHEN_IN_USE = "Kitchen code %d cannot be removed as it is in use";
    private final KitchenRepository kitchenRepository;

    public Kitchen save(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void remove(Long kitchenId) {
        try {
            kitchenRepository.deleteById(kitchenId);
        } catch (EmptyResultDataAccessException e) {
            throw new KitchenNotFoundException(kitchenId);
        } catch (DataIntegrityViolationException e) {
            throw new KitchenNotFoundException(String.format(MSG_KITCHEN_IN_USE, kitchenId));
        }
    }

    public Kitchen searchById(Long kitchenId) {
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
    }
}
