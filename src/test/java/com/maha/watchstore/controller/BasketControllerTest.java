package com.maha.watchstore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BasketController.class)
public class BasketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test(){

    }
}
