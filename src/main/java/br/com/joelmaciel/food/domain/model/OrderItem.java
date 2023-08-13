package br.com.joelmaciel.food.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String observation;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;


}
