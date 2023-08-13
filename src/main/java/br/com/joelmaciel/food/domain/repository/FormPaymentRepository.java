package br.com.joelmaciel.food.domain.repository;

import br.com.joelmaciel.food.domain.model.FormPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormPaymentRepository extends JpaRepository<FormPayment, Long> {


}
