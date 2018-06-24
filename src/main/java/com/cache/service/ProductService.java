package com.cache.service;

import com.cache.model.Product;

public interface ProductService {

    Product save(Product product);

    Product getById(long id);

    Product update(Product product);

    void delete(long id);

    void cleanCache();
}
