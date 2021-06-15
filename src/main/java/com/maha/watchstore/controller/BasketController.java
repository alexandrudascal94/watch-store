package com.maha.watchstore.controller;

import com.maha.watchstore.exception.EmptyBasketException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class BasketController {


    @PostMapping("checkout")
    public ResponseEntity<Long> checkout(@RequestBody List<Long> itemIds) {
        if(itemIds.isEmpty()){
            throw new EmptyBasketException("The item list can not be empty");
        }
        return ResponseEntity.ok().body(300L);
    }
}
