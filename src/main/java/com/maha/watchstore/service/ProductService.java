package com.maha.watchstore.service;

import com.maha.watchstore.entity.Discount;
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
public class ProductService {

    private final ProductRepository productRepository;

    public Long calculateTotalPriceFor(List<Long> productIds) {
        Map<Long, Long> productNumberMap = productIds.stream().collect(groupingBy(identity(), counting()));

        return productNumberMap.keySet()
                .stream()
                .mapToLong(id -> calculateProductPrice(productRepository.findById(id), productNumberMap.get(id)))
                .sum();
    }

    private long calculateProductPrice(Optional<Product> possibleProduct, Long numberOfProducts) {
        final Product product = possibleProduct.orElseThrow(() -> new NonExistentProductException("The product does not exist"));

        if(product.hasDiscount()){
            return calculateDiscountedPrice(product, numberOfProducts);
        }
        return product.getPrice() * numberOfProducts;
    }

    private long calculateDiscountedPrice(Product product, Long numberOfProducts) {
        Discount discount = product.getDiscount();

        long numberOfDiscounts = numberOfProducts / discount.getUnits();
        long numberOfNoDiscountedProducts = numberOfProducts - (numberOfDiscounts * discount.getUnits());

        return numberOfDiscounts * discount.getPrice() + numberOfNoDiscountedProducts * product.getPrice();
    }
}
