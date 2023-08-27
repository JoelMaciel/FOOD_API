package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.response.FormPaymentDTO;
import br.com.joelmaciel.food.domain.service.RestaurantFormPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/{restaurantId}/forms-payment")
public class RestaurantFormPaymentController {

    @Lazy
    private final RestaurantFormPaymentService restaurantFormPaymentService;

    @GetMapping
    public List<FormPaymentDTO> listAll(@PathVariable Long restaurantId) {
        return restaurantFormPaymentService.findFormsPaymentForRestaurant(restaurantId);
    }

    @PutMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantFormPaymentService.associateFormPayment(restaurantId, formPaymentId);
    }

    @DeleteMapping("/{formPaymentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long formPaymentId) {
        restaurantFormPaymentService.disassociateFormPament(restaurantId, formPaymentId);
    }

}
