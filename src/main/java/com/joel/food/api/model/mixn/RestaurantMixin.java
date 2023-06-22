package com.joel.food.api.model.mixn;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joel.food.domain.model.Address;
import com.joel.food.domain.model.FormPayment;
import com.joel.food.domain.model.Kitchen;
import com.joel.food.domain.model.Product;

import java.time.OffsetDateTime;
import java.util.List;



public abstract class RestaurantMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private Kitchen kitchen;

    @JsonIgnore
    private Address address;

   // @JsonIgnore
    private OffsetDateTime creationDate;

   // @JsonIgnore
    private OffsetDateTime updateDate;

    @JsonIgnore
    private List<Product> products;

    @JsonIgnore
    private List<FormPayment> formPayments ;

}



