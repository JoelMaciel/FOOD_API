package br.com.joelmaciel.food.api.controller;

import br.com.joelmaciel.food.api.dtos.response.PurchaseDTO;
import br.com.joelmaciel.food.api.dtos.response.PurchaseSummaryDTO;
import br.com.joelmaciel.food.domain.service.PurchaseGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseGenerationService purchaseGenerationService;

    @GetMapping
    public List<PurchaseSummaryDTO> listAll() {
        return purchaseGenerationService.listAllPurchase();
    }

    @GetMapping("/{purchaseId}")
    public PurchaseDTO findOne(@PathVariable Long purchaseId) {
        return purchaseGenerationService.findById(purchaseId);
    }
}
