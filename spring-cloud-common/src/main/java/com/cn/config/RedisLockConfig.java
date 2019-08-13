package com.cn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @author chenning
 * @Classname RedisLockConfig
 * @Description Redis分布式锁
 * @Date 2019/8/8 13:54
 */
@Configuration
public class RedisLockConfig {
    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "spring-cloud");
    }
}
