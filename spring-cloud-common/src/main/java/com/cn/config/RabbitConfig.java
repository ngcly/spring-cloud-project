package com.cn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenning
 * @Classname RabbitConfig
 * @Description RabbitMq 配置
 * @Date 2019/8/8 15:58
 */
@Configuration
@EnableRabbit //此注解很重要必须有，新版没有该注解 队列总是会生成两个channel，其中一个正常 另一个虚拟信道消费时会报代理的错误
public class RabbitConfig {
    private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    /**
     * RabbitMQ 三步骤 第一步：创建队列；第二步：创建交换机；第三步：将队列与交换机进行绑定
     * RabbitMQ 有默认的交换机 没有绑定交换机的队列，都会被自动绑定到默认交换机
     */

    /**普通队列名称*/
    public static final String NORMAL_QUEUE = "normal";

    /**广播交换机名称*/
    public static final String FANOUT_EXCHANGE = "fanout";

    /**延时队列**/
    public static final String DELAY_QUEUE = "retry-delay-queue";
    /**延时交换机**/
    public static final String DELAY_EXCHANGE = "retry-delay-exchange";
    /**延时路由名称*/
    public static final String DELAY_ROUTING_KEY = "retry-key";

    /**
     * 配置摸板（非必要 测试延迟队列方便直观）
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(messageConverter());
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    /**
     * rabbitmq 默认将信息对象按照jdk序列化 此处改为json序列化
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**默认队列 该队列会自动被 rabbitmq 绑定到默认的交换机上*/
    @Bean
    public Queue normalQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(NORMAL_QUEUE, true);
    }

    /**广播交换机*/
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    /**绑定队列与交换机*/
    @Bean
    public Binding fanoutBinding(){
        return BindingBuilder.bind(normalQueue()).to(fanoutExchange());
    }

    /**延迟队列配置 以下采用了延迟插件**/
    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE, true);
    }

    /**延迟交换机配置**/
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message",true, false, map);
    }

    /**延迟交换机与延迟队列绑定*/
    @Bean
    Binding bindingDelayExchangeQueue(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }
}
