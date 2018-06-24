package com.cache.config;

import com.cache.service.ProductService;
import com.cache.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;


@Import({
        CacheConfig.class,
        ConcurrentCacheConfig.class,
        ScheduleConfig.class
})
@Configuration
@Slf4j
public class AppConfig {

    @Bean("productService")
    public ProductService productService() {
        return new ProductServiceImpl();
    }
}
