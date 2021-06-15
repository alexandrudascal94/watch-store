package com.maha.watchstore.service.BasketService;

import com.maha.watchstore.exception.EmptyBasketException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    public Long checkout(List<Long> itemIds) {
        throw new EmptyBasketException("ItemIds list is empty");
    }
}
