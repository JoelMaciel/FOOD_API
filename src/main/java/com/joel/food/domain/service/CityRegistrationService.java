package com.joel.food.domain.service;

import com.joel.food.domain.exception.CityNotFoundException;
import com.joel.food.domain.exception.EntityInUseException;
import com.joel.food.domain.model.City;
import com.joel.food.domain.model.State;
import com.joel.food.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CityRegistrationService {

    public static final String MSG_CITY_IN_USE = "City code %d cannot be removed as it is in use";
    private final CityRepository cityRepository;
    private final StateRegistrationService stateService;

    @Transactional
    public City save(City city) {
        Long stateId = city.getState().getId();
        State state = stateService.searchById(stateId);
        city.setState(state);
        return cityRepository.save(city);
    }

    @Transactional
    public void remove(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
        } catch (EmptyResultDataAccessException e) {
            throw new CityNotFoundException(cityId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format(MSG_CITY_IN_USE, cityId));
        }
    }

    public City searchById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(
                () -> new CityNotFoundException(cityId));
    }
}
