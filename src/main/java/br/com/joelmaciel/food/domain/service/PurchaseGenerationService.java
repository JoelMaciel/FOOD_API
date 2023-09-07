package br.com.joelmaciel.food.domain.service;

import br.com.joelmaciel.food.api.dtos.response.PurchaseDTO;
import br.com.joelmaciel.food.api.dtos.response.PurchaseSummaryDTO;
import br.com.joelmaciel.food.domain.exception.PurchaseNotFoundException;
import br.com.joelmaciel.food.domain.model.Purchase;
import br.com.joelmaciel.food.domain.repository.PurchaseIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseGenerationService {

    private final PurchaseIdRepository purchaseIdRepository;

    public List<PurchaseSummaryDTO> listAllPurchase() {
        List<Purchase> listPurchase = purchaseIdRepository.findAll();
        return listPurchase.stream()
                .map(PurchaseSummaryDTO::toDTO)
                .toList();
    }

    public PurchaseDTO findById(Long purchaseId) {
        return PurchaseDTO.toDTO(searchById(purchaseId));
    }

    public Purchase searchById(Long purchaseId) {
        return purchaseIdRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException(purchaseId));
    }
}
