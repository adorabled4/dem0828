package com.dhx.dem0828.spark;


import cn.hutool.json.JSONUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author adorabled4
 * @className SparkManager
 * @date : 2023/11/17/ 10:54
 **/
@Service
public class SparkManager {

    @Resource
    SparkConfig sparkConfig;

    public String doChat(long userId, String question) throws Exception {
        // 构建鉴权url
        String authUrl = AuthUtil.genAuthUrl(sparkConfig.getApiKey(), sparkConfig.getApiSecret(),
                sparkConfig.getDefaultHostInfo().getHost(), sparkConfig.getDefaultHostInfo().getPath());
        OkHttpClient client = new OkHttpClient.Builder().build();
        // 构建聊天请求
        ChatRequest chatRequest = buildChatRequest(userId, question);
        System.out.println(JSONUtil.toJsonStr(chatRequest));
        // 构建websocket请求
        Request request = new Request.Builder().url(authUrl).build();
        SparkChatListener sparkChat = new SparkChatListener();
        // 发起websocket请求
        WebSocket webSocket = client.newWebSocket(request, sparkChat);
        webSocket.send(JSONUtil.toJsonStr(chatRequest));
        while (!sparkChat.getWsCloseFlag()) {
            Thread.sleep(200);
        }
        return sparkChat.getTotalAnswer().toString();
    }

    /**
     * 构建聊天请求
     *
     * @param userId   用户id
     * @param question 问题
     * @return {@link ChatRequest}
     */
    private ChatRequest buildChatRequest(long userId, String question) {
        return ChatRequest.builder()
                .header(ChatRequest.Header.builder()
                        .app_id(sparkConfig.getAppId())
                        .uid(String.valueOf(userId))
                        .build())
                .parameter(ChatRequest.Parameter.builder()
                        .chat(ChatRequest.Chat.builder()
                                .domain(sparkConfig.getDefaultHostInfo().getDomain())
                                .temperature(0.5)
                                .max_tokens(4096)
                                .build())
                        .build())
                .payload(ChatRequest.Payload.builder()
                        .message(ChatRequest.Message
                                .builder()
                                .text(Collections.singletonList(
                                        ChatRequest.Text.builder()
                                                .content(question)
                                                .role("user")
                                                .build()))
                                .build())
                        .build()).build();
    }

}
