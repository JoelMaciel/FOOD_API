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
public class PurchaseDTO {

    private Long id;
    private BigDecimal subtotal;
    private BigDecimal freightRate;
    private BigDecimal totalAmount;
    private String status;
    private OffsetDateTime creationDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime cancellationDate;
    private RestaurantSummaryDTO restaurant;
    private UserDTO client;
    private FormPaymentDTO formPaymentDTO;
    private AddressDTO addressDelivery;
    private List<PurchaseItemDTO> items;

    public static PurchaseDTO toDTO(Purchase purchase) {
        return PurchaseDTO.builder()
                .id(purchase.getId())
                .subtotal(purchase.getSubtotal())
                .freightRate(purchase.getFreightRate())
                .totalAmount(purchase.getTotalAmount())
                .status(purchase.getStatus().toString())
                .creationDate(purchase.getCreationDate())
                .confirmationDate(purchase.getConfirmationDate())
                .deliveryDate(purchase.getDeliveryDate())
                .cancellationDate(purchase.getCancellationDate())
                .restaurant(RestaurantSummaryDTO.toDTO(purchase.getRestaurant()))
                .client(UserDTO.toDTO(purchase.getClient()))
                .formPaymentDTO(FormPaymentDTO.toDTO(purchase.getFormPayment()))
                .addressDelivery(AddressDTO.toDTO(purchase.getDeliveryAddress()))
                .items(PurchaseItemDTO.toListDTO(purchase.getPurchaseItems()))
                .build();
    }
}
