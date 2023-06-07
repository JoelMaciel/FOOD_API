package com.joel.food.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.joel.food.FoodApiApplication;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;

public class ExclusionKitchenMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		
		
		Kitchen kitchen = new Kitchen();
		kitchen.setId(1L);
		
		kitchenRepository.remove(kitchen);
	
	}

}





