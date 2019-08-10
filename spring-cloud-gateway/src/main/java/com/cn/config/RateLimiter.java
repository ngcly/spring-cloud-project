package com.cn.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @author chenning
 * @Classname RateLimiter
 * @Description 路由限流配置
 * @Date 2019/8/5 12:14
 */
@Configuration
public class RateLimiter {

    /**
     * IP 限流 (首选)
     */
    @Bean
    @Primary
    KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

    /**
     * 用户限流
     * 根据head中的token
     */
    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getHeaders().get("Authorization").get(0));
    }

    /**
     * 接口限流
     */
    @Bean
    KeyResolver apiKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
