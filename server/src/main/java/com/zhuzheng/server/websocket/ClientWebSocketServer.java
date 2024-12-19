package com.zhuzheng.server.websocket;

import com.google.gson.Gson;
import com.zhuzheng.server.constant.MessageConstant;
import com.zhuzheng.server.dto.MessageDTO;
import com.zhuzheng.server.entity.MachineConnect;
import com.zhuzheng.server.mapper.ConnectMapper;
import com.zhuzheng.server.mapper.StatusMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket服务
 */
@Slf4j
@ServerEndpoint("/cs/{sid}")
@Component
public class ClientWebSocketServer {
    static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ClientWebSocketServer.applicationContext = applicationContext;
    }

    WebSocketHandler webSocketHandler;
    ConnectMapper connectMapper;

    //存放会话对象
    public static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        webSocketHandler = applicationContext.getBean(WebSocketHandler.class);
        connectMapper = applicationContext.getBean(ConnectMapper.class);
        sessionMap.put(sid, session);
        connectMapper.insertMachineConnect(MachineConnect.builder()
                .sid(sid)
                .connectTime(LocalDateTime.now())
                .build());
        System.out.println("客户端：" + sid + "建立连接");

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, @PathParam("sid") String sid) {
        Integer type = new Gson().fromJson(message, MessageDTO.class).getType();
        Session session = sessionMap.get(sid);
        try {
            if(type.equals(MessageConstant.HEART_BEAT_MESSAGE)){
                webSocketHandler.handleHeartBeatMessage(session, sid, message);
            } else if (type.equals(MessageConstant.STATUS_UPLOAD_MESSAGE)) {
                webSocketHandler.handleStatusUploadMessage(sid, message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 连接关闭调用的方法
     *
     * @param sid
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
        System.out.println("客户端连接断开:" + sid);
        sessionMap.remove(sid);
    }
    public static Session getSessionBySid(String sid){
        return sessionMap.get(sid);
    }

}
