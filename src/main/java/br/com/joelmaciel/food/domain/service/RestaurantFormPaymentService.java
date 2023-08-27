package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.response.FormPaymentDTO;
import br.com.joelmaciel.food.domain.model.FormPayment;
import br.com.joelmaciel.food.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RestaurantFormPaymentService {

    private final RestaurantRegistrationService restaurantRegistrationService;
    private final FormPaymentService formPaymentService;

    public List<FormPaymentDTO> findFormsPaymentForRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRegistrationService.searchById(restaurantId);
        Set<FormPayment> formPayments = restaurant.getFormPayments();
        return formPayments.stream()
                .map(FormPaymentDTO::toDTO)
                .toList();
    }

    @Transactional
    public void disassociateFormPament(Long restaurantId, Long formPaymentId) {
        Restaurant restaurant = restaurantRegistrationService.searchById(restaurantId);
        FormPayment formPayment = formPaymentService.searchById(formPaymentId);
        restaurant.removeFormPayment(formPayment);
    }

    @Transactional
    public void associateFormPayment(Long restaurantId, Long formPaymentId) {
        Restaurant restaurant = restaurantRegistrationService.searchById(restaurantId);
        FormPayment formPayment = formPaymentService.searchById(formPaymentId);
        restaurant.addFormPayment(formPayment);
    }

}

