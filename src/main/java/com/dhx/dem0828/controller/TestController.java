package com.dhx.dem0828.controller;

import com.dhx.dem0828.mq.MessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className TestController
 * @date : 2023/08/28/ 16:29
 **/
@RestController
@Slf4j
public class TestController {

    @Resource
    MessageProducer messageProducer;

    @GetMapping("/test/send")
    public String send() {
        long userId = 45376384L;
        messageProducer.sendGenChartMessage(String.valueOf(userId));
        return "send successfully";
    }
}
