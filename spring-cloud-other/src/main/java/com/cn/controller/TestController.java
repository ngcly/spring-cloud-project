package com.cn.controller;

import com.cn.client.UserClient;
import com.cn.pojo.MqMsgDO;
import com.cn.pojo.Result;
import com.cn.config.RabbitConfig;
import com.cn.pojo.UserDO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author chenning
 * @Classname Test
 * @Description 测试
 * @Date 2019/8/3 14:55
 */
@Slf4j
@Tag(name = "TestController", description = "测试API")
@RestController
@RequiredArgsConstructor
public class TestController {
    private final RedisLockRegistry redisLockRegistry;
    private final RabbitTemplate rabbitTemplate;
    private final UserClient userClient;

    @Operation(summary = "hi", description = "hi")
    @GetMapping("/hi")
    public Result hi(){
        return Result.success("hi");
    }

    /**
     * 免认证接口
     */
    @Operation(summary = "test", description = "测试")
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
                rabbitTemplate.convertAndSend(RabbitConfig.DELAY_EXCHANGE,RabbitConfig.DELAY_ROUTING_KEY,"我是延迟信息",msg->{
                    msg.getMessageProperties().setExpiration(5 * 1000 + "");
                    return msg;
                });
            }
        } catch (Exception e) {
            log.error("获取锁失败 ： ", e);
        } finally {
            lock.unlock();
        }
        return Result.success("test");
    }

    @Operation(summary = "test/d", description = "延迟队列补偿测试")
    @GetMapping("/test/d")
    public Result delayTest(){
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
//        map.add("paramtest", "123");
        MqMsgDO<MultiValueMap> dataDTO = new MqMsgDO<>("spring-cloud-user","/user/info/hehe", HttpMethod.GET,map,1);
        rabbitTemplate.convertAndSend(RabbitConfig.DELAY_EXCHANGE, RabbitConfig.DELAY_ROUTING_KEY, dataDTO, msg -> {
            msg.getMessageProperties().setHeader("x-delay", 30*1000);
            return msg;
        });
        return Result.success();
    }

    /**
     * admin 权限访问接口
     */
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result getAdmin() {
        return Result.success("拥有Admin权限可访问!!!!!!");
    }

    @Operation(summary = "根据用户名获取用户信息", description = "根据用户名获取用户信息")
    @GetMapping("/user")
    public Result<UserDO> userInfo(){
        return userClient.getUserInfoByName("admin");
    }

}
