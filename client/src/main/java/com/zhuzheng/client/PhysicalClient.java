package com.zhuzheng.client;


import com.google.gson.Gson;
import com.zhuzheng.client.constant.MessageConstant;
import com.zhuzheng.client.data.Machine;
import com.zhuzheng.client.dto.MessageDTO;
import com.zhuzheng.client.handler.MessageHandler;
import com.zhuzheng.client.timer.HeartBeatTimer;
import com.zhuzheng.client.timer.StatusTimer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicBoolean;

public class PhysicalClient extends WebSocketClient {
    // 线程安全的心跳标志位
    public AtomicBoolean heartBeatFlag = new AtomicBoolean(true);
    public Machine machine;
    public Timer heartbeatTimer;
    public Timer checkHeartBeatTimer;
    public Timer uplaodStatusTimer;
    public PhysicalClient(URI serverURI, Machine machine) {
        super(serverURI);
        this.machine = machine;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to server");
        heartBeatFlag.set(true);
        this.heartbeatTimer = HeartBeatTimer.startHeartbeat(this, machine);
        this.checkHeartBeatTimer = HeartBeatTimer.checkHeartbeat(this, machine);
        this.uplaodStatusTimer = StatusTimer.uploadState(this, machine);
    }

    @Override
    public void onMessage(String message) {
        MessageDTO mes = new Gson().fromJson(message, MessageDTO.class);
        if(mes.getType().equals(MessageConstant.HEART_BEAT_MESSAGE)){
            MessageHandler.handleHeartBeatMessage(this, message);
        } else if (mes.getType().equals(MessageConstant.COMMAND_MESSAGE)) {
            MessageHandler.handleCommandMessage(this, message);
        } else if (mes.getType().equals(MessageConstant.CONFIGURATION_MESSAGE)) {
            MessageHandler.handleConfigurationMessage(this, message);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        this.checkHeartBeatTimer.cancel();
        this.heartbeatTimer.cancel();
        this.uplaodStatusTimer.cancel();
        System.out.println("Closed with exit code " + code + " and reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public static void main(String[] args){
        Machine machine = new Machine();
        PhysicalClient client = null;
        try {
            URI uri = new URI("ws://127.0.0.1:8080/cs/" + machine.getMachineId());  // 服务器地址
            client = new PhysicalClient(uri, machine);
            client.connect();  // 连接到服务器
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
