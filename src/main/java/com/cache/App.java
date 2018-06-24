package com.cache;

import com.cache.config.AppConfig;
import com.cache.model.Product;
import com.cache.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class App {

    public static void main( String[] args ) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getEnvironment().setActiveProfiles("ehcache");
        ProductService productService = (ProductService) applicationContext.getBean("productService");

        log.info(productService.getById(2).toString());
        Thread.sleep(1000);
        log.info("Reading from cache");
        log.info(productService.getById(2).toString());

        Product product = productService.getById(3);
        product.setPrice(50);
        productService.update(product);
        log.info("Reading from cache");
        log.info(productService.getById(3).toString());

        applicationContext.close();
    }
}
