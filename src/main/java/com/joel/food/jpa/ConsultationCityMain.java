package com.joel.food.jpa;

import com.joel.food.FoodApiApplication;
import com.joel.food.domain.model.City;
import com.joel.food.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultationCityMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository cityRepository = applicationContext.getBean(CityRepository.class);

        List<City> allCities = cityRepository.list();

        for (City city : allCities) {
            System.out.printf("%s - %s\n", city.getName(), city.getState().getName());
        }
    }
}
