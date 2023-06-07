package com.joel.food.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.joel.food.FoodApiApplication;
import com.joel.food.domain.model.Restaurant;
import com.joel.food.domain.repository.RestaurantRepository;

public class ConsultationRestaurantMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

		List<Restaurant> restaurants = restaurantRepository.allRestaurants();

		for (Restaurant restaurant : restaurants) {
			System.out.printf("%s - %f - %s\n", restaurant.getName(), restaurant.getFreightRate(),
					restaurant.getKitchen().getName());
		}

	}

}
