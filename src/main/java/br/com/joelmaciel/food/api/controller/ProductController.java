package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.request.ProductRequest;
import br.com.joelmaciel.food.api.dtos.response.ProductDTO;
import br.com.joelmaciel.food.domain.service.ProductRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/{restaurantId}/products")
public class ProductController {

    private final ProductRegistrationService productService;

    @GetMapping
    public List<ProductDTO> findAll(@PathVariable Long restaurantId) {
        return productService.findAllProducts(restaurantId);
    }

    @GetMapping("/{productId}")
    public ProductDTO findByProductId(@PathVariable Long restaurantId, @PathVariable Long productId) {
        return productService.findById(restaurantId, productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@PathVariable Long restaurantId, @RequestBody @Valid ProductRequest productRequest) {
        return productService.saveProduct(restaurantId,productRequest);
    }

    @PutMapping("/{productId}")
    public ProductDTO update(@PathVariable Long restaurantId, @PathVariable Long productId,
                             @RequestBody @Valid ProductRequest productRequest) {
        return productService.updateProduct(restaurantId, productId, productRequest);
    }

}
