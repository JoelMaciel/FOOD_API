package br.com.joelmaciel.food.domain.repository;

import br.com.joelmaciel.food.domain.model.Cluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClusterRepository extends JpaRepository<Cluster, Long> {
}
