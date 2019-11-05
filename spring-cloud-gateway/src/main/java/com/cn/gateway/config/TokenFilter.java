package com.cn.gateway.config;

import com.alibaba.nacos.client.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author ngcly
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /** url匹配器*/
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private final String TOKEN_PREFIX = "bearer";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取当前请求的url，/oauth/token 为init请求 设置为白名单
        if(pathMatcher.match("/**/oauth/token/**",exchange.getRequest().getPath().value())
                ||pathMatcher.match("/**/v2/api-docs/**",exchange.getRequest().getPath().value())){
            return chain.filter(exchange);
        }

        String accessToken = extractToken(exchange.getRequest());

        String formatKey = String.format("access:%s", accessToken);
        if(StringUtils.isNotEmpty(accessToken)&&stringRedisTemplate.hasKey(formatKey)&&stringRedisTemplate.getExpire(formatKey)>0){
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -200;
    }

    /**
     * 解析 token
     */
    protected String extractToken(ServerHttpRequest request) {
        List<String> strings = request.getHeaders().get("Authorization");
        String authToken = null;
        if (strings != null && strings.get(0) != null && strings.get(0).startsWith(TOKEN_PREFIX)) {
            authToken = strings.get(0).substring(TOKEN_PREFIX.length()).trim();
        }

        if (StringUtils.isBlank(authToken)) {
            strings = request.getQueryParams().get("access_token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }
        return authToken;
    }
}