package br.com.joelmaciel.food.domain.repository;

import br.com.joelmaciel.food.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseIdRepository extends JpaRepository<Purchase, Long> {

    @Query("from Purchase p join fetch  p.client join fetch p.restaurant r join fetch r.kitchen")
    List<Purchase> findAll();
}
