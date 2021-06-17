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
        Optional<Product> expectedProduct = productRepository.findById(1L);
        assertTrue(expectedProduct.isPresent());
    }
}
