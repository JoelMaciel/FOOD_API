package br.com.joelmaciel.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private BigDecimal subTotal;
    private BigDecimal freightRate;
    private BigDecimal totalAmount;

    @Embedded
    private Address deliveryAddress;

    private StatusOrder status;

    @CreationTimestamp
    private LocalDateTime creationDate;

    private OffsetDateTime confirmationDate;
    private OffsetDateTime cancellationDate;
    private OffsetDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormPayment formPayment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


}








