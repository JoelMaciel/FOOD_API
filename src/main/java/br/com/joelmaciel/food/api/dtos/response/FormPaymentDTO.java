package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.FormPayment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FormPaymentDTO {

    private Long id;
    private String description;

    public static FormPaymentDTO toDTO(FormPayment formPayment) {
        return FormPaymentDTO.builder()
                .id(formPayment.getId())
                .description(formPayment.getDescription())
                .build();
    }
}
