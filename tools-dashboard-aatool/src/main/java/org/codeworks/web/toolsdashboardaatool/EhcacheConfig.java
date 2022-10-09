package org.codeworks.web.toolsdashboardaatool;

import org.ehcache.config.builders.CacheManagerBuilder;
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

// 暂不使用Ehcache, 由于数据量较多且可能重启服务,避免丢失使用分离服务的Redis作为缓存
@Configuration
//@EnableCaching
public class EhcacheConfig {

    @Bean
    public org.ehcache.CacheManager ehcacheCacheManager(){
        org.ehcache.CacheManager manager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        return manager;
    }

    //下面是开启Spring Cache的注入, 现使用Redisson替换

//    @Bean
//    public JCacheCacheManager jcacheCacheManager() {
//        JCacheCacheManager cm = new JCacheCacheManager();
//        cm.setCacheManager(jsr107cacheManager());
//        return cm;
//    }
//
//    @Bean
//    public CacheManager jsr107cacheManager() {
//        //http://blog.csdn.net/pmlpml/article/details/53116377
//        //http://www.ehcache.org/documentation/3.4/107.html
//        CachingProvider provider = Caching.getCachingProvider();
//        CacheManager cacheManager = provider.getCacheManager();
//
//        MutableConfiguration<Long, String> configuration =
//                new MutableConfiguration<Long, String>()
//                        // Cannot set type for store! this may be a bug in spring or ehCache
//                        //.setTypes(Long.class, String.class)
//                        .setStoreByValue(false)
//                        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(
//                                new Duration(TimeUnit.DAYS, 7L)));
//
//        cacheManager.createCache("CacheDSLCountCreator", configuration);
//        cacheManager.createCache("CacheDSLCountInviter", configuration);
//        cacheManager.createCache("CacheDSLCountJoiner", configuration);
//        cacheManager.createCache("CacheDSLCountPublishCampaign", configuration);
//        cacheManager.createCache("CacheDSLCountAAWorkShop", configuration);
//        cacheManager.createCache("CacheDSLCountTotalCampaign", configuration);
//        cacheManager.createCache("CacheDSLCountTotalUser", configuration);
//        cacheManager.createCache("CacheDSLCountUser", configuration);
//        cacheManager.createCache("CacheDSLCountApply", configuration);
//        cacheManager.createCache("CacheDSLCountApplyCampaign", configuration);
//        cacheManager.createCache("CacheDSLCountRegister", configuration);
//        cacheManager.createCache("CacheDSLCountRegisterTimes", configuration);
//        cacheManager.createCache("CacheDSLCountActiveCampaign", configuration);
//        cacheManager.createCache("CacheDSLCountAAWorkShopActive", configuration);
//        cacheManager.createCache("CacheDSLCountInviteShareTimes", configuration);
//        cacheManager.createCache("CacheDSLCountVisitorShareTimes", configuration);
//        cacheManager.createCache("CacheDSLCountABOShareTimes", configuration);
//        cacheManager.createCache("CacheDSLCountChannelApply", configuration);
//
//        return cacheManager;
//    }
}
