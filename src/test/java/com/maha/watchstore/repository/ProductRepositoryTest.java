package com.maha.watchstore.repository;

import com.maha.watchstore.entity.Product;
import com.maha.watchstore.respository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void getProductById_should_returnCorrespondingProduct() {
        long existingProductId = 1L;
        Optional<Product> expectedProduct = productRepository.findById(existingProductId);
        assertTrue(expectedProduct.isPresent());
    }

    @Test
    void getProductById_nonExistingId_should_returnEmpty() {
        long nonExistingProductId = -1L;
        Optional<Product> expectedProduct = productRepository.findById(nonExistingProductId);
        assertTrue(expectedProduct.isEmpty());
    }

    @Test
    void getProductById_withDiscount_should_returnProductWithDiscount() {
        long nonExistingProductId = 1L;
        Optional<Product> expectedProduct = productRepository.findById(nonExistingProductId);
        assertTrue(expectedProduct.get().hasDiscount());
    }

    @Test
    void getProductById_withoutDiscount_should_returnProduct() {
        long nonExistingProductId = 3L;
        Optional<Product> expectedProduct = productRepository.findById(nonExistingProductId);
        assertTrue(expectedProduct.isPresent());
    }
}
