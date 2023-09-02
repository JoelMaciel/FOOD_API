package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.request.ProductRequest;
import br.com.joelmaciel.food.api.dtos.response.ProductDTO;
import br.com.joelmaciel.food.domain.exception.ProductNotFoundException;
import br.com.joelmaciel.food.domain.model.Product;
import br.com.joelmaciel.food.domain.model.Restaurant;
import br.com.joelmaciel.food.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRegistrationService {

    private final ProductRepository productRepository;
    private final RestaurantRegistrationService restaurantService;
    private final EntityManagerFactory entityManagerFactory;

    public List<ProductDTO> findAllProducts(Long restaurantId) {
        Restaurant restaurant = restaurantService.searchById(restaurantId);
        List<Product> products = productRepository.findByRestaurant(restaurant);
        return products.stream()
                .map(ProductDTO::toDTO)
                .toList();
    }

    public ProductDTO findById(Long restaurantId, Long productId) {
        Restaurant restaurant = restaurantService.searchById(restaurantId);
        Product product = searchById(restaurantId, productId);
        return ProductDTO.toDTO(product);
    }

    @Transactional
    public ProductDTO saveProduct(Long restaurantId, ProductRequest productRequest) {
        Restaurant restaurant = restaurantService.searchById(restaurantId);
        Product product = ProductRequest.toEntity(productRequest);
        product.setRestaurant(restaurant);
        return ProductDTO.toDTO(productRepository.save(product));
    }

    @Transactional
    public ProductDTO updateProduct(Long restaurantId, Long productId, ProductRequest productRequest) {
        Product product = searchById(restaurantId, productId).toBuilder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .active(productRequest.getActive())
                .build();

        return ProductDTO.toDTO(productRepository.save(product));

    }

    public Product searchById(Long restaurantId, Long productId) {
        return productRepository.findByIdAndRestaurantId(productId, restaurantId)
                .orElseThrow(() -> new ProductNotFoundException(restaurantId, productId));
    }

}
