package com.dhx.dem0828.mq;

import com.dhx.dem0828.exception.GenException;
import com.dhx.dem0828.model.MQConstant;
import com.dhx.dem0828.model.UserEntity;
import com.dhx.dem0828.service.UserService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className MessageConsumer
 * @date : 2023/08/28/ 16:17
 **/
@Component
@Slf4j
public class MessageConsumer {

    @Resource
    UserService userService;

    int count = 1;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = MQConstant.QUEUE_NAME),
            exchange = @Exchange(value = MQConstant.EXCHANGE_NAME,type = ExchangeTypes.DIRECT), key = MQConstant.ROUTE_KEY))
    @Retryable(value = GenException.class, maxAttempts = 5,backoff = @Backoff(delay = 1000*60))
    private void demoListener(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliverTag) {
        log.info("{}th run demoListener",count++);
        UserEntity user = new UserEntity();
        if (message != null) {
            user.setUserId(Long.parseLong(message));
            user.setUserName("Job");
            user.setEmail("xxx@gmail.com");
            if (userService == null) {
                throw new RuntimeException("userService is null");
            }else{
                userService.printUser(user);
            }
        }
    }

}
