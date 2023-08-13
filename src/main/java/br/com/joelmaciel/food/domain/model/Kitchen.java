package br.com.joelmaciel.food.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Kitchen {

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "kitchen")
	private List<Restaurant> restaurants = new ArrayList<>();

}







