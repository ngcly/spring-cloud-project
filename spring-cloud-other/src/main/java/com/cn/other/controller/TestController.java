package com.cn.other.controller;

import com.cn.common.pojo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenning
 * @Classname Test
 * @Description 测试
 * @Date 2019/8/3 14:55
 */
@Api(value = "TestController", tags = "测试API")
@RestController
public class TestController {

    @ApiOperation(value = "hi", notes = "测试")
    @GetMapping("/hi")
    public Result test(){
        return Result.success("hi");
    }
}
