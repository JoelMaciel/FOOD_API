package com.joel.food.infrastructure.repository;

import com.joel.food.domain.model.State;
import com.joel.food.domain.repository.StateRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> list() {
        return manager.createQuery("from State", State.class)
                .getResultList();
    }

    @Override
    public State find(Long id) {
        return manager.find(State.class, id);
    }

    @Transactional
    @Override
    public State save(State state) {
        return manager.merge(state);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        State state = find(id);

        if (state == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(state);
    }
}
