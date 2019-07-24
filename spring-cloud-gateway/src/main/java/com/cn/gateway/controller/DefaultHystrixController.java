package com.cn.gateway.controller;

import com.cn.common.pojo.RestCode;
import com.cn.common.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenning
 * @Classname DefaultHystrixController
 * @Description 默认熔断控制层
 * @Date 2019/7/23 20:33
 */
@RestController
public class DefaultHystrixController {

    @RequestMapping("/defaultFallback")
    public Result fallback(){
        return Result.failure(RestCode.SERVICE_UNAVAILABLE);
    }
}
