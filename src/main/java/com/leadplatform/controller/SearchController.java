package com.leadplatform.controller;

import com.leadplatform.entity.Product;
import com.leadplatform.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
public class SearchController {

    private ProductSearchService searchService;

    @Autowired
    public SearchController(ProductSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/products")
    @ResponseBody
    public List<Product> fetchByNameOrDesc(@RequestParam(value = "q", required = false) String query) {
        List<Product> products = searchService.processSearch(query) ;
        return products;
    }

    @GetMapping("/suggestions")
    @ResponseBody
    public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {
        List<String> suggests = searchService.fetchSuggestions(query);
        return suggests;
    }

}
