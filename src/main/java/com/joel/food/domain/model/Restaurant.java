package com.joel.food.domain.model;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "freight_rate", nullable = false)
	private BigDecimal freightRate;

	@ManyToOne
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;

}



