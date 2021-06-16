package com.maha.watchstore.service;

import com.maha.watchstore.entity.Product;
import com.maha.watchstore.respository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BasketService.class})
public class BasketServiceTest {

    @MockBean
    private ProductRepository productRepositoryMock;

    @Autowired
    private BasketService serviceUnderTest;

    @Test
    public void calculatePriceFor_singleItemNoDiscount_ShouldReturn_itemPrice() {
        Assertions.assertEquals(200L, serviceUnderTest.calculatePriceFor(List.of(1L)));
    }

    @Test
    public void calculatePriceFor_multipleDifferentItemsNoDiscount_ShouldReturn_totalPricePrice() {
        Product productOne = new Product();
        productOne.setPrice(100L);
        Product productTwo = new Product();
        productTwo.setPrice(200L);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(productOne));
        when(productRepositoryMock.findById(2L)).thenReturn(Optional.of(productTwo));

        Assertions.assertEquals(300L, serviceUnderTest.calculatePriceFor(List.of(1L, 2L)));
    }
}

