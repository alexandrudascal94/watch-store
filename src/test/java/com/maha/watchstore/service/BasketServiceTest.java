package com.maha.watchstore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BasketServiceTest {

    @Autowired
    private BasketService serviceUnderTest;

    @Test
    public void calculatePriceFor_singleItemNoDiscount_ShouldReturn_itemPrice() {
        Assertions.assertEquals(200L, serviceUnderTest.calculatePriceFor(List.of(1L)));
    }

    @Test
    public void calculatePriceFor_multipleDifferentItemsNoDiscount_ShouldReturn_totalPricePrice() {
        Assertions.assertEquals(300L, serviceUnderTest.calculatePriceFor(List.of(1L, 2L, 3L)));
    }
}

