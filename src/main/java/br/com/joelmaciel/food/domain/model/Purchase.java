package br.com.joelmaciel.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal freightRate;
    private BigDecimal totalAmount;

    @Embedded
    private Address deliveryAddress;

    @Enumerated(EnumType.STRING)
    private StatusOrder status = StatusOrder.CREATED;

    @CreationTimestamp
    private OffsetDateTime creationDate;

    private OffsetDateTime confirmationDate;
    private OffsetDateTime cancellationDate;
    private OffsetDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormPayment formPayment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseItem> purchaseItems = new ArrayList<>();

    public void calculateTotalValue() {
        this.subtotal = getPurchaseItems().stream()
                .map(PurchaseItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalAmount = this.subtotal.add(freightRate);
    }

    public void defineRate() {
        setFreightRate(getRestaurant().getFreightRate());
    }

    public void assignOrdersToItems() {
        getPurchaseItems().forEach(item -> item.setPurchase(this));
    }


}








