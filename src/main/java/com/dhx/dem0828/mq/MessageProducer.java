package com.dhx.dem0828.mq;

import com.dhx.dem0828.model.MQConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className MessageProducer
 * @date : 2023/08/28/ 16:26
 **/
@Component
public class MessageProducer {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 发送生成图表消息
     *
     * @param message 消息
     */
    public void sendGenChartMessage(String message){
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME,MQConstant.ROUTE_KEY,message);
    }
}
