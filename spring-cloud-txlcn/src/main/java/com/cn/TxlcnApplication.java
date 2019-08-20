package com.cn;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenning
 * @Classname TxlcnApplication
 * @Description 分布式事务管理启动类
 * @Date 2019/8/20 14:32
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class TxlcnApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxlcnApplication.class, args);
    }
}
