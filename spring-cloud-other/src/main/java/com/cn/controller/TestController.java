package com.cn.controller;

import com.cn.pojo.Result;
import com.cn.config.RabbitConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author chenning
 * @Classname Test
 * @Description 测试
 * @Date 2019/8/3 14:55
 */
@Slf4j
@Api(value = "TestController", tags = "测试API")
@RestController
public class TestController {
    @Autowired
    private RedisLockRegistry redisLockRegistry;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "hi", notes = "hi")
    @GetMapping("/hi")
    public Result hi(){
        return Result.success("hi");
    }

    /**
     * 免认证接口
     */
    @ApiOperation(value = "test", notes = "测试")
    @GetMapping("/test/t")
    public Result test(){
        //redis 分布式锁
        Lock lock = redisLockRegistry.obtain("lockKey");
        try {
            log.info("开始争抢锁 ");
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                //争抢到锁
                log.info("争抢到锁 ");
                // do business
                rabbitTemplate.convertAndSend(RabbitConfig.NORMAL_QUEUE,"MQ发消息啦");
            }
        } catch (Exception e) {
            log.error("获取锁失败 ： ", e);
        } finally {
            lock.unlock();
        }
        return Result.success("test");
    }

    /**
     * admin 权限访问接口
     */
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAdmin() {
        return "拥有Admin权限可访问!!!!!!";
    }

    /**
     * 获取用户凭证
     */
    @RequestMapping("/principle")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("principal.toString() " + principal.toString());
        log.info("principal.getName() " + principal.getName());
        log.info("authentication: " + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }
}
