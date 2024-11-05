package com.aashik.priceEngine.controller;

import com.aashik.priceEngine.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
@AllArgsConstructor
public class ProductController {

    private final PriceService priceService;

    @GetMapping("/{productName}/{quantity}")
    public ResponseEntity<Double> getPrice(@PathVariable String productName, @PathVariable int quantity){
        double price = priceService.calculatePrice(productName, quantity);
        return ResponseEntity.ok(price);
    }
}
