package com.dhx.dem0828.mqtt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className MqttController
 * @date : 2023/09/21/ 18:51
 **/
@RequestMapping("/mqtt")
@RestController
@Api(value = "mqtt测试接口")
public class MqttController {

    @Resource
    private MqttServer mqttServer;

    /**
     * 发送消息
     *
     * @param topic 主题
     * @param qos   消息级别
     * @return
     */
    @GetMapping(value = "/send")
    @ApiOperation("发布消息")
    public String testRec(String topic, String message, int qos) {
        mqttServer.publishConnect();
        mqttServer.sendMQTTMessage(topic, message, 0);
        String data = "发送了一条主题是‘" + topic + "’，内容是:" + message + "，消息级别 " + qos;
        return data;
    }


    @GetMapping(value = "/sub")
    @ApiOperation("订阅topic")
    public String testSub(String topic) {
        mqttServer.init(topic, 2);
        mqttServer.subscribeConnect();
        String data = "订阅主题: " + topic + " , 成功";
        return data;
    }

}
