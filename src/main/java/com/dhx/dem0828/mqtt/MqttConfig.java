package com.dhx.dem0828.mqtt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author adorabled4
 * @className MqttConfig
 * @date : 2023/09/21/ 18:26
 **/

@Configuration
@ConfigurationProperties(MqttConfig.PREFIX)
@Getter
@Setter
public class MqttConfig {
    //指定配置文件application-local.properties中的属性名前缀
    public static final String PREFIX = "mqtt";
    private String host;
    private String clientid;
    private String username;
    private String password;
    private boolean cleansession;
    private String default_topic;
    private int timeout;
    private int keepalive;
    private int connectionTimeout;

}

