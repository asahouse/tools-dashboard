package org.codeworks.web.toolsdashboardbaidu;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@EnableCaching
@Configuration
public class RedissonConfig {

    @Autowired
    private Environment env;

    @Bean
    public CacheManager cacheManager() throws IOException {
        Long ttl = 7*24*60*60*1000L; //1week
        Long maxIdleTime = 30*24*60*1000L;//30day

        CacheConfig commonConfig = new CacheConfig(ttl, maxIdleTime);

        Map<String, CacheConfig> configMap = new HashMap<>();
            configMap.put("baidu-statistics-sites", commonConfig);
            configMap.put("baidu-statistics-datas", commonConfig);
            configMap.put("baidu-statistics-datas-collect", commonConfig);
            configMap.put("baidu-statistics-userinfo", commonConfig);


        return new RedissonSpringCacheManager(redissonClient(), configMap);
    }

    @Bean(destroyMethod="shutdown")
    public RedissonClient redissonClient() throws IOException {
        String[] profiles = env.getActiveProfiles();
        String profile = "";
        if(profiles.length > 0) {
            profile = "-" + profiles[0];
        }

        Config config = Config.fromYAML(new ClassPathResource("redisson" + profile + ".yml").getInputStream());

        Optional.ofNullable(env.getProperty("docker.enable")).ifPresent(enable -> {
            Boolean dockerEnable = Boolean.valueOf(enable);
            log.debug(String.format("dockerEnable : %s", enable));
            if (dockerEnable) {
                Optional.ofNullable(env.getProperty("docker.redis.dns")).ifPresent(dns -> {
                    log.debug(String.format("docker.redis.dns : %s", dns));
                    config.useSingleServer().setAddress(String.format("redis://%s:6379", dns));
                });
            }
        });

        return Redisson.create(config);
    }
}
