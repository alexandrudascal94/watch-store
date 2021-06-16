package com.maha.watchstore.service;

import com.maha.watchstore.entity.Product;
import com.maha.watchstore.exception.NonExistentProductException;
import com.maha.watchstore.respository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BasketService.class})
public class BasketServiceTest {

    @MockBean
    private ProductRepository productRepositoryMock;

    @Autowired
    private BasketService serviceUnderTest;

    private Product productOne = new Product();
    private Product productTwo = new Product();

    @Test
    public void calculatePriceFor_singleItemNoDiscount_ShouldReturn_itemPrice() {
        productOne.setPrice(200L);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(productOne));
        assertEquals(200L, serviceUnderTest.calculatePriceFor(List.of(1L)));
    }

    @Test
    public void calculatePriceFor_multipleDifferentProductNoDiscount_ShouldReturn_totalPricePrice() {
        productOne.setPrice(100L);
        productTwo.setPrice(200L);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(productOne));
        when(productRepositoryMock.findById(2L)).thenReturn(Optional.of(productTwo));

        assertEquals(300L, serviceUnderTest.calculatePriceFor(List.of(1L, 2L)));
    }

    @Test
    public void calculatePriceFor_NonExistentProduct_ShouldThrow_NonExistentProductException() {
        when(productRepositoryMock.findById(-1L)).thenThrow(NonExistentProductException.class);
        assertThrows(NonExistentProductException.class, () -> serviceUnderTest.calculatePriceFor(List.of(-1L)));
    }

    @Test
    public void calculatePriceFor_multipleSameProducts_ShouldReturn_totalPrice() {
        productOne.setPrice(100L);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(productOne));
        assertEquals(300L, serviceUnderTest.calculatePriceFor(List.of(1L, 1L, 1L)));
    }
}

