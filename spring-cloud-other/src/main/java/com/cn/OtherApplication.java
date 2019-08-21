package com.cn;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chenning
 * @Classname OtherApplication
 * @Description 其他服务启动类
 * @Date 2019/8/3 14:48
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
//@EnableDistributedTransaction //TX_LCN 有bug 跟zipkin有冲突
public class OtherApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtherApplication.class, args);
    }
}
