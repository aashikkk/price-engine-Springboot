package com.aashik.priceEngine.service;

import com.aashik.priceEngine.model.Product;
import com.aashik.priceEngine.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceService {
    private final ProductRepository productRepository;

    public double calculatePrice(String name, int quantity){
        Product product = productRepository.findByName(name);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        int cartons = quantity / product.getUnitsPerCarton();
        int singleUnits = quantity % product.getUnitsPerCarton();

        double price = cartons * product.getCartonPrice();
        if (cartons >=3){
            price *= 0.9; // 10% discount for 3 or more cartons, price for only cartons
        }

        price += singleUnits * product.getCartonPrice() * 1.3; // 30% increase for single units, and total price
        return price;
    }
}
