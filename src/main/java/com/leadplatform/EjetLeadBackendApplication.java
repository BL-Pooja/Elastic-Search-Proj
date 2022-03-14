package com.leadplatform;


import com.leadplatform.entity.Product;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.leadplatform.repository.ProductRepository;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableScheduling
public class EjetLeadBackendApplication extends SpringBootServletInitializer {

    private static final String COMMA_DELIMITER = ",";

    @Autowired
    private ElasticsearchOperations esOps;

    @Autowired
    private ProductRepository productRepo;

    public static void main(String[] args) {
        SpringApplication.run(EjetLeadBackendApplication.class, args);
    }



    @PreDestroy
    public void deleteIndex() {
        esOps.indexOps(Product.class).delete();
    }


    @PostConstruct
    public void buildIndex() {

        esOps.indexOps(Product.class).refresh();
        productRepo.deleteAll();
        productRepo.saveAll(prepareDataset());
    }

    private Collection<Product> prepareDataset() {
        Resource resource = new ClassPathResource("fashion-products.csv");
        List<Product> productList = new ArrayList<Product>();

        try (
                InputStream input = resource.getInputStream();
                Scanner scanner = new Scanner(resource.getInputStream());) {
            int lineNo = 0;
            while (scanner.hasNextLine()) {
                ++lineNo;
                String line = scanner.nextLine();
                if(lineNo == 1) continue;
                Optional<Product> product =
                        csvRowToProductMapper(line);
                if(product.isPresent())
                    productList.add(product.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    private Optional<Product> csvRowToProductMapper(final String line) {
        try (
                Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                String name = rowScanner.next();
                String description = rowScanner.next();
                String manufacturer = rowScanner.next();
                return Optional.of(
                        Product.builder()
                                .name(name)
                                .description(description)
                                .manufacturer(manufacturer)
                                .build());

            }
        }
        return Optional.of(null);
    }
}
