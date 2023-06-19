package com.joel.food.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joel.food.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Kitchen {

	@NotNull(groups = Groups.KitchenId.class)
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "kitchen")
	private List<Restaurant> restaurants = new ArrayList<>();

}







