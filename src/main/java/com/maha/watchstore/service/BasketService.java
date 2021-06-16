package com.maha.watchstore.service;


import com.maha.watchstore.exception.UnsupportedBasketItemsException;
import com.maha.watchstore.respository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketService {

    private final ProductRepository productRepository;

    public Long calculatePriceFor(List<Long> itemIds) {
        return itemIds.stream().mapToLong(id -> productRepository.findById(id)
                .orElseThrow(() -> new UnsupportedBasketItemsException("One item could not be find"))
                .getPrice())
                .sum();
    }
}
