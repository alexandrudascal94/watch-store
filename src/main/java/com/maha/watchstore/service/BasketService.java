package com.maha.watchstore.service;

import com.maha.watchstore.entity.Product;
import com.maha.watchstore.exception.NonExistentProductException;
import com.maha.watchstore.respository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
@AllArgsConstructor
public class BasketService {

    private final ProductRepository productRepository;

    public Long calculatePriceFor(List<Long> itemIds) {
        Map<Long, Long> itemsCountMap = itemIds.stream().collect(groupingBy(identity(), counting()));

        return itemsCountMap.keySet()
                .stream()
                .mapToLong(id -> getPriceWithDiscount(productRepository.findById(id), itemsCountMap.get(id)))
                .sum();
    }

    private long getPriceWithDiscount(Optional<Product> product, Long count) {
        final long productPrice = product.orElseThrow(() -> new NonExistentProductException("The product does not exist")).getPrice();
        return productPrice * count;
    }
}
