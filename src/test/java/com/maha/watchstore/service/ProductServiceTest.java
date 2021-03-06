package com.maha.watchstore.service;

import com.maha.watchstore.entity.Discount;
import com.maha.watchstore.entity.Product;
import com.maha.watchstore.exception.NonExistingProductException;
import com.maha.watchstore.respository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ProductService.class})
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepositoryMock;

    @Autowired
    private ProductService serviceUnderTest;

    private Product productOne;
    private Product productTwo;
    private Discount discountOne;
    private Discount discountTwo;


    @BeforeEach
    public void beforeAll() {
        productOne = new Product();
        productTwo = new Product();
        discountOne = new Discount();
        discountTwo = new Discount();
    }

    @Test
    public void calculatePriceFor_singleProductNoDiscount_ShouldReturn_productPrice() {
        productOne.setPrice(200L);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(productOne));
        assertEquals(200L, serviceUnderTest.calculateTotalPriceFor(List.of(1L)));
    }

    @Test
    public void calculatePriceFor_multipleDifferentProductsNoDiscount_ShouldReturn_totalPrice() {
        productOne.setPrice(100L);
        productTwo.setPrice(200L);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(productOne));
        when(productRepositoryMock.findById(2L)).thenReturn(Optional.of(productTwo));

        assertEquals(300L, serviceUnderTest.calculateTotalPriceFor(List.of(1L, 2L)));
    }

    @Test
    public void calculatePriceFor_nonExistingProduct_ShouldThrow_NonExistingProductException() {
        when(productRepositoryMock.findById(-1L)).thenThrow(NonExistingProductException.class);
        assertThrows(NonExistingProductException.class, () -> serviceUnderTest.calculateTotalPriceFor(List.of(-1L)));
    }

    @Test
    public void calculatePriceFor_multipleSameProducts_ShouldReturn_totalPrice() {
        productOne.setPrice(100L);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(productOne));
        assertEquals(300L, serviceUnderTest.calculateTotalPriceFor(List.of(1L, 1L, 1L)));
    }

    @Test
    public void calculatePriceFor_discountedProducts_ShouldReturn_discountedPrice() {
        productOne.setPrice(100L);
        discountOne.setUnits(3);
        discountOne.setPrice(200L);
        productOne.setDiscount(discountOne);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(productOne));
        assertEquals(200L, serviceUnderTest.calculateTotalPriceFor(List.of(1L, 1L, 1L)));
    }

    @Test
    public void calculatePriceFor_discountedAndNotDiscountedProducts_ShouldReturn_correctPrice() {
        productOne.setPrice(100L);
        discountOne.setUnits(3);
        discountOne.setPrice(200L);
        productOne.setDiscount(discountOne);
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(productOne));
        assertEquals(300L, serviceUnderTest.calculateTotalPriceFor(List.of(1L, 1L, 1L, 1L)));
    }

    @Test
    public void calculatePriceFor_differentDiscountedProducts_ShouldReturn_correctPrice() {
        discountOne.setUnits(3);
        discountOne.setPrice(200L);
        productOne.setPrice(100L);
        productOne.setDiscount(discountOne);

        productTwo.setPrice(200L);
        discountTwo.setUnits(2);
        discountTwo.setPrice(300L);
        productTwo.setDiscount(discountTwo);

        when(productRepositoryMock.findById(1L)).thenReturn(Optional.ofNullable(productOne));
        when(productRepositoryMock.findById(2L)).thenReturn(Optional.ofNullable(productTwo));

        assertEquals(800L, serviceUnderTest.calculateTotalPriceFor(List.of(1L, 1L, 1L, 1L, 2L, 2L, 2L)));
    }
}

