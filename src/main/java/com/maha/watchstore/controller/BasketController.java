package com.maha.watchstore.controller;

import com.maha.watchstore.dto.Checkout;
import com.maha.watchstore.exception.UnsupportedBasketItemsException;
import com.maha.watchstore.service.BasketService.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
@AllArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping("checkout")
    public ResponseEntity<Checkout> checkout(@RequestBody List<Long> itemIds) {
        if(itemIds.isEmpty()){
            throw new UnsupportedBasketItemsException("The item list can not be empty");
        }

        return ResponseEntity.ok().body(
                Checkout.builder()
                        .price(basketService.calculatePriceFor(itemIds))
                        .build());
    }
}
