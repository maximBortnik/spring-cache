package com.cache.service;

import com.cache.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ProductServiceImpl implements ProductService {

    private Map<Long, Product> productDB = new ConcurrentHashMap<>();

    @PostConstruct
    public void setUp() {
        productDB.put(1L, new Product(1, "pr1", 10));
        productDB.put(2L, new Product(2, "pr2", 20));
        productDB.put(3L, new Product(3, "pr3", 30));
    }

    @Override
    public Product save(Product product) {
        log.info("saving new product");
        product.setId(4);
        return productDB.put(4L, product);
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public Product getById(long id) {
        log.info("Getting product from db by id {}", id);
        if (productDB.containsKey(id)) {
            return productDB.get(id);
        }

        throw new IllegalArgumentException("Product does not exist!");
    }

    @Override
    @CachePut(value = "products", key = "#product.id")
    public Product update(Product product) {
        long id = product.getId();
        if (productDB.containsKey(id)) {
            log.info("Updating product by id {}", id);
            Product pr = productDB.get(id);
            pr.setName(product.getName());
            pr.setPrice(product.getPrice());
            return productDB.put(id, pr);
        }
        throw new IllegalArgumentException("Product does not exist!");
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void delete(long id) {
        log.info("Deleting product by id {}", id);
        productDB.remove(id);
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    @CacheEvict(value = "products", allEntries = true)
    public void cleanCache() { }
}
