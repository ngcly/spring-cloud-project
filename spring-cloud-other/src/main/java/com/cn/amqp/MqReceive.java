package com.cn.amqp;

import com.alibaba.fastjson.JSONObject;
import com.cn.config.RabbitConfig;
import com.cn.pojo.MqMsgDO;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author chenning
 * @Classname MqReceive
 * @Description 队列接收端
 * @Date 2019/8/8 17:01
 */
@Component
public class MqReceive {
    private static final Logger log = LoggerFactory.getLogger(MqReceive.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RabbitTemplate rabbitTemplate;

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
    public void consumeDelay(MqMsgDO dataDO, Message message, Channel channel) {
        log.info("补偿开始调用接口开始：{}",dataDO.toString());
        JSONObject result;
        try {
            switch (dataDO.getMethod()){
                case GET:
                    Map<String,Object> map = (Map<String, Object>) dataDO.getParam();
                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://"+dataDO.getServeName()+dataDO.getUrl());
                    map.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
                    result = restTemplate.getForObject(builder.build().encode().toString(),JSONObject.class,dataDO.getParam());
                    break;
                default:
                    result = restTemplate.postForObject(dataDO.getServeName()+dataDO.getUrl(),dataDO.getParam(),JSONObject.class);
                    break;
            }
            log.info("补偿调用接口返回：{}",result.toJSONString());
            if (result!=null&&result.getInteger("code")==0) {
                //通知 MQ 消息已被成功消费,可以ACK了
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }else{
                //若请求失败
                retry(dataDO);
            }
        }catch (Exception e){
            retry(dataDO);
        }
    }

    /**重试*/
    private void retry(MqMsgDO dataDO){
        dataDO.setFailNum(dataDO.getFailNum()+1);
        if(dataDO.getFailNum()<11){
            //重新入队 等待下次尝试
            rabbitTemplate.convertAndSend(RabbitConfig.DELAY_EXCHANGE, RabbitConfig.DELAY_ROUTING_KEY, dataDO, msg -> {
                // 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
                msg.getMessageProperties().setHeader("x-delay",dataDO.getFailNum() * 1000 * 60);
                return msg;
            });
        }else{
            log.info("失败次数达到10次，入库备份，人工解决：{}",dataDO.toString());
        }
    }
}
