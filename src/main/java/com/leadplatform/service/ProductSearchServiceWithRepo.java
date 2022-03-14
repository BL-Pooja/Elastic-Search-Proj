package com.leadplatform.service;

import com.leadplatform.entity.Product;
import com.leadplatform.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchServiceWithRepo {

    private ProductRepository productRepository;

    public void createProductIndexBulk(final List<Product> products) {
        productRepository.saveAll(products);
    }

    public void createProductIndex(final Product product) {
        productRepository.save(product);
    }
}
