package com.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@EnableCaching
@Slf4j
@Profile("concurrent")
public class ConcurrentCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        log.info("Initializing ConcurrentMapCache");
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("products")));
        return cacheManager;
    }
}
