package com.joel.food.domain.service;

import com.joel.food.domain.exception.EntityInUseException;
import com.joel.food.domain.exception.EntityNotExistsException;
import com.joel.food.domain.model.City;
import com.joel.food.domain.model.State;
import com.joel.food.domain.repository.CityRepository;
import com.joel.food.domain.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityRegistrationService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new EntityNotExistsException(
                        String.format("There is no record of state with code %d", stateId)));

        city.setState(state);
        return cityRepository.save(city);
    }

    public void remove(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotExistsException(
                    String.format("There is no record of city with code %d", cityId));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("City code %d cannot be removed as it is in use", cityId));
        }
    }
}
