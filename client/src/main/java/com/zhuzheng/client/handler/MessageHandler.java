package com.zhuzheng.client.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuzheng.client.PhysicalClient;
import com.zhuzheng.client.data.Configuration;
import com.zhuzheng.client.dto.MessageDTO;
import com.zhuzheng.client.timer.HeartBeatTimer;
import com.zhuzheng.client.timer.StatusTimer;

/**
 * ClassName: MessageHandler
 * Package: com.zhuzheng.client.handler
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/17 - 21:32
 * Version: v1.0
 */
public class MessageHandler {

    public static void handleHeartBeatMessage(PhysicalClient physicalClient, String message) {
        physicalClient.heartBeatFlag.set(true);
        System.out.println("收到服务端返回的心跳包: " + message);  // 处理收到的消息
    }

    public static void handleCommandMessage(PhysicalClient physicalClient, String message) {
        // 使用 TypeToken 来解析带泛型的类
        Gson gson = new Gson();
        TypeToken<MessageDTO<String>> typeToken = new TypeToken<>(){};
        MessageDTO<String> messageDTO = gson.fromJson(message, typeToken.getType());
        String command = messageDTO.getData();
        System.out.println("收到服务端的命令: " + command);
        //暂且只做这一个命令的处理
        if("close".equals(command)){
            physicalClient.close();
        }
    }

    public static void handleConfigurationMessage(PhysicalClient physicalClient, String message) {
        // 使用 TypeToken 来解析带泛型的类
        Gson gson = new Gson();
        TypeToken<MessageDTO<Configuration>> typeToken = new TypeToken<>(){};
        MessageDTO<Configuration> messageDTO = gson.fromJson(message, typeToken.getType());
        Configuration configuration = messageDTO.getData();
        physicalClient.machine.setConfiguration(configuration);
        System.out.println("收到服务端的配置信息: " + configuration);

        physicalClient.uplaodStatusTimer.cancel();
        StatusTimer.uploadState(physicalClient, physicalClient.machine);
        physicalClient.heartbeatTimer.cancel();
        HeartBeatTimer.startHeartbeat(physicalClient, physicalClient.machine);
        physicalClient.checkHeartBeatTimer.cancel();
        HeartBeatTimer.checkHeartbeat(physicalClient, physicalClient.machine);

    }
}
