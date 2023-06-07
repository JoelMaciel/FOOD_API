package com.joel.food.infrastructure.repository;

import com.joel.food.domain.model.FormPayment;
import com.joel.food.domain.repository.FormPaymentRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormPaymentRepositoryImpl implements FormPaymentRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormPayment> list() {
        return manager.createQuery("from FormPayment", FormPayment.class)
                .getResultList();
    }

    @Override
    public FormPayment find(Long id) {
        return manager.find(FormPayment.class, id);
    }

    @Transactional
    @Override
    public FormPayment save(FormPayment formPayment) {
        return manager.merge(formPayment);
    }

    @Transactional
    @Override
    public void remove(FormPayment formPayment) {
        formPayment = find(formPayment.getId());
        manager.remove(formPayment);
    }

}
