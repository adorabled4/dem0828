package com.dhx.dem0828.controller;

import com.dhx.dem0828.manager.WebSocketServer;
import com.dhx.dem0828.mq.MessageProducer;
import com.dhx.dem0828.spark.SparkManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

    @Resource
    WebSocketServer webSocketServer;

    @Resource
    SparkManager sparkManager;

    @GetMapping("/test/send")
    public String send() {
        long userId = 45376384L;
        messageProducer.sendGenChartMessage(String.valueOf(userId));
        return "send successfully";
    }

    @GetMapping("/test/send/ws/{userId}")
    public String send2WS(@PathVariable long userId) {
        try {
            webSocketServer.sendMessage("this is a message from server!", new HashSet<>(Arrays.asList(String.valueOf(userId))));
            return "success";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/test/spark")
    public String chat(@RequestParam("question") String question) {
        try {
            return sparkManager.doChat(132, question);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
