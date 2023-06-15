package com.joel.food.domain.repository;

import com.joel.food.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("from Restaurant r join fetch r.kitchen")
    List<Restaurant> findAll();

    List<Restaurant> findByFreightRateBetween(BigDecimal rateInitial, BigDecimal rateFinal);

    @Query("from Restaurant where name like %:name% and kitchen.id = :id")
    List<Restaurant> searchByName(String name, @Param("id") Long kitcheId);

    Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

    List<Restaurant> findTop2ByNameContaining(String name);

    int countByKitchenId(Long id);


}
