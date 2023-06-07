package com.joel.food.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.joel.food.FoodApiApplication;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.repository.KitchenRepository;

public class InclusionKitchenMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
		
		
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Brazilian");
		
		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("Japanese");
		
		kitchen1 = kitchenRepository.add(kitchen1);
		kitchen2 =  kitchenRepository.add(kitchen2);
		
		System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
		System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());

	}

}





