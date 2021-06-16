package com.maha.watchstore.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;



import java.util.List;

@SpringBootTest
public class BasketServiceTest {

    @Autowired
    private BasketService serveUnderTest;

    @Test
    public void calculatePriceFor_singleItem_ShouldReturn_itemPrice() {
        Assertions.assertEquals(200L, serveUnderTest.calculatePriceFor(List.of(1L)));
    }
}
