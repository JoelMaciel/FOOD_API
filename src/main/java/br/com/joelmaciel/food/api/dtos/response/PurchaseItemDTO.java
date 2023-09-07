package br.com.joelmaciel.food.api.dtos.response;

import br.com.joelmaciel.food.domain.model.PurchaseItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class PurchaseItemDTO {

    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal priceTotal;
    private String observation;

    public static List<PurchaseItemDTO> toListDTO(List<PurchaseItem> purchaseItems) {
        return purchaseItems.stream()
                .map(purchaseItem -> PurchaseItemDTO.builder()
                        .productId(purchaseItem.getId())
                        .productName(purchaseItem.getProduct().getName())
                        .quantity(purchaseItem.getQuantity())
                        .unitPrice(purchaseItem.getUnitPrice())
                        .priceTotal(purchaseItem.getTotalPrice())
                        .observation(purchaseItem.getObservation())
                        .build())
                .toList();
    }


}
