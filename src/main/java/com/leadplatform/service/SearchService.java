package com.leadplatform.service;

import com.leadplatform.entity.Product;
import com.leadplatform.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;

    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public SearchService(ProductRepository productRepository, ElasticsearchOperations elasticsearchOperations) {
        super();
        this.productRepository = productRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }



    public List<Product> fetchProductNames(final String name){

        return productRepository.findByManufacturerAndCategory(name, "");
    }

    public List<Product> fetchProductNamesContaining(final String name){



        return productRepository.findByNameContaining(name);
    }
}
