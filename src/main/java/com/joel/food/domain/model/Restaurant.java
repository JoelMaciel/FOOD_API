package com.joel.food.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@JsonIgnore
	@Embedded
	private Address address;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurant_form_payment",
	joinColumns = @JoinColumn(name = "restaurant_Id"),
	inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
	private List<FormPayment> formPayments = new ArrayList<>();

}



