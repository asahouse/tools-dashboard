package org.codeworks.web.toolsdashboardaatool.utils;


import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.ResourceType;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.expiry.Expiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by benjaminkc on 17/12/6
 *
 * 单独使用Ehcache特性的工具类
 */
@Component
public class EhcacheUtil {

    @Autowired
    CacheManager ehcacheCacheManager;

    private int defaulEntrySize = 1000;
    private int defaulTimeToLive = 3600;//1小时

    public void put(String cacheName, String key, Object value, int size, int second) {
        Cache cache;
        if (this.containCache(cacheName))
            cache = ehcacheCacheManager.getCache(cacheName, String.class, Serializable.class);
        else {
            Expiry ttlExpiry = Expirations.timeToLiveExpiration(new Duration(second, TimeUnit.SECONDS));
            cache = ehcacheCacheManager.createCache(cacheName,
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Serializable.class,
                            ResourcePoolsBuilder.newResourcePoolsBuilder()
                                    .with(ResourceType.Core.HEAP, size, EntryUnit.ENTRIES, false))
                            .withExpiry(ttlExpiry));
        }
        cache.put(key, value);
    }

    public void put(String cacheName, String key, Object value) {
        this.put(cacheName, key, value, defaulEntrySize, defaulTimeToLive);
    }

    public Boolean containCache(String cacheName) {
        Cache cache = ehcacheCacheManager.getCache(cacheName, String.class, Serializable.class);
        return Optional.ofNullable(cache).isPresent();
    }

    public Boolean containKey(String cacheName, String key) {
        Cache cache = ehcacheCacheManager.getCache(cacheName, String.class, Serializable.class);
        if (this.containCache(cacheName))
            return cache.containsKey(key);
        else return false;
    }

    public Optional get(String cacheName, String key) {
        Cache cache = ehcacheCacheManager.getCache(cacheName, String.class, Serializable.class);
        return Optional.ofNullable(cache).isPresent() && cache.containsKey(key) ? Optional.ofNullable(cache.get(key)) : Optional.empty();
    }

    public void remove(String cacheName, String key) {
        Cache cache = ehcacheCacheManager.getCache(cacheName, String.class, Serializable.class);
        if (Optional.ofNullable(cache).isPresent() && cache.containsKey(key)) cache.remove(key);
    }
}
