package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.Purchase;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PurchaseSummaryDTO {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal freightRate;
    private BigDecimal totalAmount;
    private String status;
    private OffsetDateTime creationDate;
    private RestaurantSummaryDTO restaurant;
    private UserDTO client;

    public static PurchaseSummaryDTO toDTO(Purchase purchase) {
        return PurchaseSummaryDTO.builder()
                .id(purchase.getId())
                .subtotal(purchase.getSubtotal())
                .freightRate(purchase.getFreightRate())
                .totalAmount(purchase.getTotalAmount())
                .status(purchase.getStatus().toString())
                .creationDate(purchase.getCreationDate())
                .restaurant(RestaurantSummaryDTO.toDTO(purchase.getRestaurant()))
                .client(UserDTO.toDTO(purchase.getClient()))
                .build();
    }
}
