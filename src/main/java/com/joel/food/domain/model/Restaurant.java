package com.joel.food.domain.model;

import com.joel.food.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@PositiveOrZero
	@NotNull
	@Column(name = "freight_rate", nullable = false)
	private BigDecimal freightRate;


	@Valid
	@ConvertGroup(from = Default.class, to = Groups.KitchenId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;


	@Embedded
	private Address address;


	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime creationDate;


	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime updateDate;


	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();


	@ManyToMany
	@JoinTable(name = "restaurant_form_payment",
	joinColumns = @JoinColumn(name = "restaurant_Id"),
	inverseJoinColumns = @JoinColumn(name = "form_payment_id"))
	private List<FormPayment> formPayments = new ArrayList<>();

}



