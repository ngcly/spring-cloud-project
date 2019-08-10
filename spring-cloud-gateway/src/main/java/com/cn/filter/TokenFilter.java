package com.cn.filter;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cn.pojo.RestCode;
import com.cn.pojo.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author chenning
 * @Classname TokenFilter
 * @Description 全局过滤器
 * @Date 2019/7/12 16:17
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    /** url匹配器*/
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    private final String TOKEN_PREFIX = "Bearer";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取当前请求的url，/oauth/token 为init请求 设置为白名单
        if(pathMatcher.match("/**/oauth/token/**",exchange.getRequest().getPath().value())
                ||pathMatcher.match("/**/v2/api-docs/**",exchange.getRequest().getPath().value())){
            return chain.filter(exchange);
        }

        String accessToken = extractToken(exchange.getRequest());

        if(StringUtils.isNotEmpty(accessToken)){
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        //无token，返回未登录信息
        return exchange.getResponse().writeWith(Flux.just(this.getBodyBuffer(exchange.getResponse(), Result.failure(RestCode.UNAUTHEN))));
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

    /**
     * 封装返回值
     */
    private DataBuffer getBodyBuffer(ServerHttpResponse response, Result result) {
        return response.bufferFactory().wrap(JSON.toJSONBytes(result));
    }
}
