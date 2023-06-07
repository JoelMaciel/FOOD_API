package com.joel.food.jpa;

import com.joel.food.FoodApiApplication;
import com.joel.food.domain.model.FormPayment;
import com.joel.food.domain.repository.FormPaymentRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultationFormPaymentMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormPaymentRepository formPaymentRepository = applicationContext.getBean(FormPaymentRepository.class);

        List<FormPayment> allPaymentForms = formPaymentRepository.list();

        for (FormPayment formPayment : allPaymentForms) {
            System.out.println(formPayment.getDescription());
        }
    }

}
