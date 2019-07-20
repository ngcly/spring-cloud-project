package com.cn.zuul.filter;

import com.cn.common.pojo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author chenning
 * @Classname TokenFilter
 * @Description token 过滤器
 * @Date 2019/7/6 17:18
 */
//@Component
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /**
         * pre：路由之前
         * routing：路由之时
         * post： 路由之后
         * error：发送错误调用
         */
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 过滤器执行顺序，数字越小，优先级越高，越靠前
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 返回 true，拦截所有 URL
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        String token = request.getParameter("access_token");
        if (StringUtils.isEmpty(token)) {
            System.out.println("进入spring-cloud-zuul服务，执行TokenFilter，Token为空！");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(Result.failure(401, "token为空，无权访问！"));
                requestContext.getResponse().setContentType("application/json;charset=UTF-8");
                requestContext.setResponseBody(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        System.out.println("进入spring-cloud-zuul服务，执行TokenFilter，Token is OK!");
        return null;
    }
}
