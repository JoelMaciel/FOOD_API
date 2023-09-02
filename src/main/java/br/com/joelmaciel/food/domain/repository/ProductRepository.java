package br.com.joelmaciel.food.domain.repository;

import br.com.joelmaciel.food.domain.model.Product;
import br.com.joelmaciel.food.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*  Essa query também resolveria  a consulta esperada.
    *   @Query("from Product where restaurant.id = :restaurant and id = :product")
        Optional<Product> findById(@Param("restaurant") Long restaurantId, @Param("product") Long productId);
    * */

    Optional<Product> findByIdAndRestaurantId(Long productId, Long restaurantId);

    List<Product> findByRestaurant(Restaurant restaurant);
}
