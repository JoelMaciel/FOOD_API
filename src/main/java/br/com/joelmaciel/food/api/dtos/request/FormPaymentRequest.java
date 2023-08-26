package br.com.joelmaciel.food.api.dtos.request;

import br.com.joelmaciel.food.domain.model.FormPayment;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormPaymentRequest {

    @NotBlank
    private String description;

    public static FormPayment toEntity(FormPaymentRequest formPaymentRequest) {
        return FormPayment.builder()
                .description(formPaymentRequest.getDescription())
                .build();
    }
}
