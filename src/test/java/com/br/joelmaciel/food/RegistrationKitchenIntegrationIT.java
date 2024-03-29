package com.br.joelmaciel.food;

import br.com.joelmaciel.food.domain.exception.EntityInUseException;
import br.com.joelmaciel.food.domain.exception.KitchenNotFoundException;
import br.com.joelmaciel.food.domain.model.Kitchen;
import br.com.joelmaciel.food.domain.service.KitchenRegistrationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class RegistrationKitchenIntegrationIT {

    @Autowired
    private KitchenRegistrationService kitchenService;

    @Test
    public void mustRegisterKitchenSuccessfully() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName("Chinesa");

        kitchenService.save(kitchen);

        assertThat(kitchen).isNotNull();
        assertThat(kitchen.getId()).isNotNull();

    }

    @Test
    public void mustFailWhenAssigningUnnamedkitchen() {
        Kitchen kitchen = new Kitchen();
        kitchen.setName(null);

        ConstraintViolationException expectedError = Assertions.assertThrows(ConstraintViolationException.class,
                () -> {
                    kitchenService.save(kitchen);
                });

        assertThat(expectedError).isNotNull();

    }

    @Test
    public void mustFailWhenDeleteKitchenInUse() {

      EntityInUseException expectedError = Assertions.assertThrows(EntityInUseException.class, () -> {
          kitchenService.remove(1L);
      });

      assertThat(expectedError).isNotNull();
    }

    @Test
    public void mustFailWhenDeletingNonExistingKitchen() {
        KitchenNotFoundException expectedError = Assertions.assertThrows(KitchenNotFoundException.class, () -> {
            kitchenService.remove(10L);
        });
        assertThat(expectedError).isNotNull();
    }

}
