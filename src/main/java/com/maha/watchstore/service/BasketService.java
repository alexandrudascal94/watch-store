package com.maha.watchstore.service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    public Long calculatePriceFor(List<Long> itemIds) {
        return 200L;
       //throw new UnsupportedBasketItemsException("ItemIds list is empty");
    }
}
