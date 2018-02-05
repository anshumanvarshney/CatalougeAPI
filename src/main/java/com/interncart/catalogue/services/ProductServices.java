package com.interncart.catalogue.services;

import com.interncart.catalogue.entity.Product;

import java.util.Map;

public interface ProductServices {

    void add(Product product);
    void remove(Product product);
    void remove(String productId);
    void updateQuantity(String productId, int delta);
    void updateBulk(Map<String, Integer> updateMap);
}
