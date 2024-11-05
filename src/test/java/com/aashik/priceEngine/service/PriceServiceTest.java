package com.aashik.priceEngine.service;

import com.aashik.priceEngine.model.Product;
import com.aashik.priceEngine.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    private PriceService priceService;
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        priceService = new PriceService(productRepository);
    }

    @Test
    public void testCalculatePrice_withValidProduct() {
        Product product = new Product(1,"TestProduct", 10, 100.0);
        when(productRepository.findByName("TestProduct")).thenReturn(product);

        double price = priceService.calculatePrice("TestProduct", 30);

        assertEquals(270.0, price); // 3 cartons with 10% discount
    }

    @Test
    public void testCalculatePrice_withSingleUnits() {
        Product product = new Product(1, "TestProduct", 20, 175.0);
        when(productRepository.findByName("TestProduct")).thenReturn(product);

        double price = priceService.calculatePrice("TestProduct", 5);

        assertEquals(1137.50, price); // 3 cartons with 10% discount + 5 single units with 30% increase
    }

    @Test
    public void testCalculatePrice_productNotFound() {
        when(productRepository.findByName("NonExistentProduct")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            priceService.calculatePrice("NonExistentProduct", 10);
        });
    }
}