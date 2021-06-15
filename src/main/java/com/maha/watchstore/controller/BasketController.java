package com.maha.watchstore.controller;

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
    public ResponseEntity<Long> checkout(@RequestBody List<Long> itemIds){
        return ResponseEntity.ok().body(300L);
    }
}
