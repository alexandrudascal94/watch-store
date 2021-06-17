package com.maha.watchstore.repository;

import com.maha.watchstore.entity.Product;
import com.maha.watchstore.respository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
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
    void getProductById_NonExistingId_should_returnEmpty() {
        long nonExistingProductId = -1L;
        Optional<Product> expectedProduct = productRepository.findById(nonExistingProductId);
        assertTrue(expectedProduct.isEmpty());
    }
}
