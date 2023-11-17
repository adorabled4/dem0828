package com.dhx.dem0828.spark;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import com.dhx.dem0828.spark.ChatResponse.*;

import java.io.IOException;
import java.util.*;

/**
 * @author adorabled4
 * @className SparkManager
 * @date : 2023/11/17/ 10:54
 **/
@Slf4j
@Data
public class SparkChatListener extends WebSocketListener {
    public StringBuilder totalAnswer = new StringBuilder();

    /**
     * websocket连接关闭标志
     */
    private Boolean wsCloseFlag=false;
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        ChatResponse chatResponse = JSONUtil.toBean(text, ChatResponse.class);
        if (chatResponse.getHeader().getCode() != 0) {
            log.info("发生错误，错误码为：" + chatResponse.getHeader().getCode());
            log.info("本次请求的sid为：" + chatResponse.getHeader().getSid());
            webSocket.close(1000, chatResponse.getHeader().getMessage());
        }
        List<ContentRoleIndex> textList = chatResponse.getPayload().getChoices().getText();
        for (ContentRoleIndex temp : textList) {
            totalAnswer.append(temp.getContent());
        }
        if (chatResponse.getHeader().getStatus() == 2) {
            // 可以关闭连接，释放资源
            ContentRoleIndex contentRoleIndex = new ContentRoleIndex();
            contentRoleIndex.setRole("assistant");
            contentRoleIndex.setContent(totalAnswer.toString());
            wsCloseFlag = true;
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        if (response != null) {
            int code = response.code();
            try {
                log.info("[Spark-chat-websocket]failure code:{} , body{}", code, response.body().string());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
