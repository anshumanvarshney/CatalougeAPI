package com.interncart.catalogue.services.Impl;

import com.interncart.catalogue.Values;
import com.interncart.catalogue.entity.Product;
import com.interncart.catalogue.repository.ProductRepository;
import com.interncart.catalogue.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void add(Product product) {
        if (product.getpUnit() == null)
            product.setpUnit(0);
        if (product.getPimage() == null)
            product.setPimage(Values.DEFAULT_PRODUCT_IMAGE);
        product.setDateCreated(Date.from(Instant.now()));
        productRepository.save(product);
    }

    @Override
    public void remove(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void remove(String productId) {
        productRepository.delete(productId);
    }

    @Override
    public void updateQuantity(String productId, int delta) {
        Product product = productRepository.findOne(productId);
        if (product == null)
            return;
        product.setpUnit(product.getpUnit() + delta);
        add(product);
    }

    @Override
    public void updateBulk(Map<String, Integer> updateMap) {
        updateMap.forEach((pid, delta) ->{
            updateQuantity(pid, delta);
        });
    }
}
