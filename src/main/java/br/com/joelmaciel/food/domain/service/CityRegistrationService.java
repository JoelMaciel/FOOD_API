package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.request.CityRequest;
import br.com.joelmaciel.food.api.dtos.request.StateIdRequest;
import br.com.joelmaciel.food.api.dtos.response.CityDTO;
import br.com.joelmaciel.food.domain.exception.*;
import br.com.joelmaciel.food.domain.model.City;
import br.com.joelmaciel.food.domain.model.State;
import br.com.joelmaciel.food.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityRegistrationService {

    public static final String MSG_CITY_IN_USE = "City code %d cannot be removed as it is in use";
    private final CityRepository cityRepository;
    private final StateRegistrationService stateService;

    public List<CityDTO> getALlCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(CityDTO::toDTO)
                .toList();
    }

    public CityDTO findById(Long cityId) {
        return CityDTO.toDTO(searchById(cityId));
    }

    @Transactional
    public CityDTO save(CityRequest cityRequest) {
        try {
            State state = stateService.searchById(cityRequest.getState().getId());
            cityRequest.getState().setName(state.getName());
            return CityDTO.toDTO(cityRepository.save(CityRequest.toEntity(cityRequest)));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @Transactional
    public CityDTO updateCity(Long cityId, CityRequest cityRequest) {
        City city = searchById(cityId).toBuilder()
                .name(cityRequest.getName())
                .state(StateIdRequest.toModel(cityRequest.getState()))
                .build();
        try {
            stateService.searchById(cityRequest.getState().getId());
            return CityDTO.toDTO(cityRepository.save(city));
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void remove(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
            cityRepository.flush();
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
