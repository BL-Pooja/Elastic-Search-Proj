package com.leadplatform.controller;


import com.leadplatform.entity.Product;
import com.leadplatform.service.SearchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
public class UIController {

    private  SearchService searchService;

    @Autowired
    public UIController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String home(Model model) {
        List<Product> products = searchService.fetchProductNamesContaining("Hornby");

        List<String> names = products.stream().flatMap(prod->{
            return Stream.of(prod.getName());
        }).collect(Collectors.toList());
        model.addAttribute("names", names);
        return "search";
    }
}
