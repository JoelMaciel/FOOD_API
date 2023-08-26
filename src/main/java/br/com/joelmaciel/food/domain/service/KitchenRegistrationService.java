package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.request.KitchenRequest;
import br.com.joelmaciel.food.api.dtos.response.KitchenDTO;
import br.com.joelmaciel.food.domain.model.Kitchen;
import br.com.joelmaciel.food.domain.repository.KitchenRepository;
import br.com.joelmaciel.food.domain.exception.EntityInUseException;
import br.com.joelmaciel.food.domain.exception.KitchenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitchenRegistrationService {

    public static final String MSG_KITCHEN_IN_USE = "Kitchen code %d cannot be removed as it is in use";
    private final KitchenRepository kitchenRepository;

    public List<KitchenDTO> findAllKitchens() {
        List<Kitchen> kitchens = kitchenRepository.findAll();
        return kitchens.stream()
                .map(KitchenDTO::toDTO)
                .toList();
    }

    public KitchenDTO findById(Long kitchenId) {
        return KitchenDTO.toDTO(searchById(kitchenId));
    }

    @Transactional
    public KitchenDTO save(KitchenRequest kitchenRequest) {
        Kitchen kitchen = kitchenRepository.save(KitchenRequest.toEntity(kitchenRequest));
        return KitchenDTO.toDTO(kitchen);
    }

    @Transactional
    public KitchenDTO updateKitchen(Long kitchenId, KitchenRequest kitchenRequest) {
        Kitchen kitchen = searchById(kitchenId).toBuilder()
                .name(kitchenRequest.getName())
                .build();

        return KitchenDTO.toDTO(kitchenRepository.save(kitchen));
    }

    @Transactional
    public void remove(Long kitchenId) {
        try {
            kitchenRepository.deleteById(kitchenId);
            kitchenRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new KitchenNotFoundException(kitchenId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(MSG_KITCHEN_IN_USE, kitchenId));
        }
    }

    public Kitchen searchById(Long kitchenId) {
        return kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
    }
}
