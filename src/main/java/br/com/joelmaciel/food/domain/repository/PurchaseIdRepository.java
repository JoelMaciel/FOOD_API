package br.com.joelmaciel.food.domain.repository;

import br.com.joelmaciel.food.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseIdRepository extends JpaRepository<Purchase, Long> {
}
