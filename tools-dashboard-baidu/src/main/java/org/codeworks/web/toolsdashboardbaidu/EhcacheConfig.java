package org.codeworks.web.toolsdashboardbaidu;

import org.ehcache.config.builders.CacheManagerBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.util.concurrent.TimeUnit;

/**
 * 配置Spring JCache的默认缓存方案
 * jsr107实现为Ehcache
 */

@Configuration
//@EnableCaching
public class EhcacheConfig {

    @Bean
    public org.ehcache.CacheManager ehcacheCacheManager(){
        org.ehcache.CacheManager manager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        return manager;
    }
    /*
    @Bean
    public JCacheCacheManager jcacheCacheManager() {
        JCacheCacheManager cm = new JCacheCacheManager();
        cm.setCacheManager(jsr107cacheManager());
        return cm;
    }

    @Bean
    public CacheManager jsr107cacheManager() {
        //http://blog.csdn.net/pmlpml/article/details/53116377
        //http://www.ehcache.org/documentation/3.4/107.html
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();

//        MutableConfiguration<Long, String> configuration =
//                new MutableConfiguration<Long, String>()
//                        // Cannot set type for store! this may be a bug in spring or ehCache
//                        //.setTypes(Long.class, String.class)
//                        .setStoreByValue(false)
//                        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(
//                                new Duration(TimeUnit.DAYS, 7L)));
//
//        cacheManager.createCache("CacheDSLCountCreator", configuration);
        return cacheManager;
    }
    */
}
