package com.maha.watchstore.service;

import com.maha.watchstore.exception.UnsupportedBasketItemsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    public Long calculatePriceFor(List<Long> itemIds) {
        throw new UnsupportedBasketItemsException("ItemIds list is empty");
    }
}