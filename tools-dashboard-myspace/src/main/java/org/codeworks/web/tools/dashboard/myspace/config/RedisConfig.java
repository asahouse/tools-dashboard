package org.codeworks.web.tools.dashboard.myspace.config;

import org.apache.commons.lang3.StringUtils;
import org.codeworks.web.tools.dashboard.myspace.cache.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 */
@Configuration
@EnableCaching
public class RedisConfig {

    Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisPool redisPoolFactory() {
        logger.info("connect redis ：" + host + ":" + port + " done.");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(false);
        JedisPool jedisPool;
        if(StringUtils.isEmpty(password)){
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        }else{
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        }
        return jedisPool;
    }

    @Bean
    public RedisCacheManager redisCacheManager(JedisPool jedisPool){
        return new RedisCacheManager(jedisPool);
    }

}
