package com.joel.food.domain.repository;

import com.joel.food.domain.model.FormPayment;

import java.util.List;

public interface FormPaymentRepository {

    List<FormPayment> list();
    FormPayment find(Long id);
    FormPayment save(FormPayment formPayment);
    void remove(FormPayment formPayment);
}
