package com.hbtheme.inventoryservice.service;

import com.hbtheme.coreapi.dto.InventoryResponse;
import com.hbtheme.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory -> InventoryResponse.builder()
                                        .skuCode(inventory.getSkuCode())
                                        .isInnStock(inventory.getQuantity()>0)
                                        .build()
                ) .toList();
    }
}
