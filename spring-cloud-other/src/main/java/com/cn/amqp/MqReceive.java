package com.cn.amqp;

import com.cn.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author chenning
 * @Classname MqReceive
 * @Description 队列接收端
 * @Date 2019/8/8 17:01
 */
@Component
public class MqReceive {
    private static final Logger log = LoggerFactory.getLogger(MqReceive.class);

    @RabbitListener(queues = {RabbitConfig.NORMAL_QUEUE})
    public void consume(String msg,Message message, Channel channel) {
        log.info("[listenerManualAck 监听的消息] - [{}]", msg);
        try {
            //通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {RabbitConfig.DELAY_QUEUE})
    public void consumeDelay(String msg,Message message, Channel channel) {
        log.info("[listenerManualAck 监听的延迟消息] - [{}]", msg);
        try {
            //通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
            e.printStackTrace();
        }
    }
}
