package com.dhx.dem0828.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author adorabled4
 * @className MqttConnect
 * @date : 2023/09/21/ 18:30
 **/
@Component
public class MqttConnect {

    @Autowired
    private MqttConfig config;

    public MqttConnect(MqttConfig config) {
        this.config = config;
    }

    public MqttConnectOptions getOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(config.isCleansession());
        options.setUserName(config.getUsername());
        options.setPassword(config.getPassword().toCharArray());
        options.setConnectionTimeout(config.getConnectionTimeout());
        //设置心跳
        options.setKeepAliveInterval(config.getKeepalive());
        return options;
    }

    public MqttConnectOptions getOptions(MqttConnectOptions options) {

        options.setCleanSession(options.isCleanSession());
        options.setUserName(options.getUserName());
        options.setPassword(options.getPassword());
        options.setConnectionTimeout(options.getConnectionTimeout());
        options.setKeepAliveInterval(options.getKeepAliveInterval());
        return options;
    }

}
