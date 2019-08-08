package com.cn.other.controller;

import com.cn.common.pojo.Result;
import com.cn.other.config.RabbitConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
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
@Api(value = "TestController", tags = "测试API")
@RestController
public class TestController {
    @Autowired
    private RedisLockRegistry redisLockRegistry;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @ApiOperation(value = "hi", notes = "hi")
    @GetMapping("/hi")
    public Result hi(){
        return Result.success("hi");
    }

    @ApiOperation(value = "test", notes = "测试")
    @GetMapping("/test")
    public Result test(){
        //redis 分布式锁
        Lock lock = redisLockRegistry.obtain("lockKey");
        try {
            logger.info("开始争抢锁 ");
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                //争抢到锁
                logger.info("争抢到锁 ");
                // do business
                rabbitTemplate.convertAndSend(RabbitConfig.NORMAL_QUEUE,"MQ发消息啦");
            }
        } catch (Exception e) {
            logger.error("获取锁失败 ： ", e);
        } finally {
            lock.unlock();
        }
        return Result.success("test");
    }

}
