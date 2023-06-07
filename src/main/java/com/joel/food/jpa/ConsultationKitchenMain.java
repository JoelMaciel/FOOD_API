package com.joel.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.joel.food.FoodApiApplication;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;

public class ConsultationKitchenMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		
		List<Kitchen> kitchens = kitchenRepository.allKitchens();
		
		for(Kitchen kitchen : kitchens) {
			System.out.println(kitchen.getName());
		}

	}

}





