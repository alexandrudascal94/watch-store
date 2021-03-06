package com.maha.watchstore.controller;

import com.maha.watchstore.exception.UnsupportedItemException;
import com.maha.watchstore.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class BasketControllerTest {

    public static final String CHECKOUT_ENDPOINT = "/checkout";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void noItemsCheckout_ShouldReturn_BadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/checkout")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void unsupportedItemsCheckout_ShouldReturn_BadRequest() throws Exception {
        when(productService.calculateTotalPriceFor(any())).thenThrow(new UnsupportedItemException("Requested items are not valid"));
        mockMvc.perform(MockMvcRequestBuilders.post(CHECKOUT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[-1, -3]"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void itemsCheckout_ShouldReturn_totalPrice() throws Exception {
        when(productService.calculateTotalPriceFor(any())).thenReturn(200L);
        mockMvc.perform(MockMvcRequestBuilders.post(CHECKOUT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[1, 2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price", Matchers.is(200)))
                .andReturn();
    }
}
