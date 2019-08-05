package com.cn.gateway.filter;

import java.util.List;
import java.util.Map;

import com.cn.common.exception.GlobalException;
import com.cn.common.pojo.RestCode;
import com.cn.gateway.remote.UserClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author chenning
 * @Classname TokenFilter
 * @Description 过滤器
 * @Date 2019/7/12 16:17
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    /** url匹配器*/
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private UserClient userClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取当前请求的url，/oauth/token 为init请求 设置为白名单
        if(pathMatcher.match("/**/oauth/token/**",exchange.getRequest().getPath().value())
                ||pathMatcher.match("/**/v2/api-docs/**",exchange.getRequest().getPath().value())){
            return chain.filter(exchange);
        }

        String accessToken = extractToken(exchange.getRequest());

        if(StringUtils.isNotEmpty(accessToken)){
            //远程调用授权服务校验token是否失效
            Map<String,?> checkToken = userClient.checkToken(accessToken);

            if(checkToken!=null){
                if(Boolean.parseBoolean(String.valueOf(checkToken.get("active")))){
                    //Token有效
                    return chain.filter(exchange);
                }
            }else {
                throw new GlobalException(503,"授权服务熔断");
            }
        }
        //网关拒绝，返回401
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().writeWith(Flux.error(new GlobalException(RestCode.UNAUTHEN)));
    }

    @Override
    public int getOrder() {
        return -200;
    }

    protected String extractToken(ServerHttpRequest request) {
        List<String> strings = request.getHeaders().get("Authorization");
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0).substring("Bearer".length()).trim();
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
