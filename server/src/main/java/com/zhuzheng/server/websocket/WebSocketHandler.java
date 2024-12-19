package com.zhuzheng.server.websocket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuzheng.server.constant.MessageConstant;
import com.zhuzheng.server.dto.MachineDTO;
import com.zhuzheng.server.dto.MessageDTO;
import com.zhuzheng.server.dto.message.Status;
import com.zhuzheng.server.dto.message.Configuration;
import com.zhuzheng.server.entity.MachineStatus;
import com.zhuzheng.server.mapper.ConnectMapper;
import com.zhuzheng.server.mapper.StatusMapper;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * ClassName: WebSocketHandler
 * Package: com.zhuzheng.server.websocket
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/17 - 21:08
 * Version: v1.0
 */
@Slf4j
@Component
public class WebSocketHandler {
    @Autowired
    ConnectMapper connectMapper;
    @Autowired
    StatusMapper statusMapper;

    public void handleHeartBeatMessage(Session session, String sid, String message) throws IOException {
        log.info("收到来自客户端{}的心跳消息",sid);
        session.getBasicRemote().sendText(message);
    }

    public void handleStatusUploadMessage(String sid, String message) {
        // 使用 TypeToken 来解析带泛型的类
        Gson gson = new Gson();
        TypeToken<MessageDTO<Status>> typeToken = new TypeToken<>(){};
        MessageDTO<Status> messageDTO = gson.fromJson(message, typeToken.getType());
        Status data = (Status) messageDTO.getData();
        log.info("收到来自客户端{}上传的状态数据{}",sid,data);
        statusMapper.insertMachineStatus(MachineStatus.builder()
                .sid(sid)
                .cpuUsage(data.getCpuUsage())
                .diskUsage(data.getDiskUsage())
                .freeDiskSpace(data.getFreeDiskSpace())
                .uploadTime(LocalDateTime.now())
                .freeMemory(data.getFreeMemory())
                .totalDiskSpace(data.getTotalDiskSpace())
                .totalMemory(data.getTotalMemory())
                .memoryUsage(data.getMemoryUsage())
                .build());
    }

    public void sendControllCommandToClient(Session session, String sid, String command) throws IOException {
        MessageDTO<String> messageDTO = new MessageDTO<>(MessageConstant.COMMAND_MESSAGE, command);
        String jsonMessage = new Gson().toJson(messageDTO);
        log.info("向客户端{}发送控制命令{}",sid,command);
        session.getBasicRemote().sendText(jsonMessage);

    }
    public void sendConfigurationToClient(Session session, String sid, Configuration configuration) throws IOException {
        MessageDTO<Configuration> messageDTO = new MessageDTO<>(MessageConstant.CONFIGURATION_MESSAGE, configuration);
        String jsonMessage = new Gson().toJson(messageDTO);
        log.info("向客户端{}发送配置信息{}", sid, configuration);
        session.getBasicRemote().sendText(jsonMessage);
    }
    public void sendMachineToBrowse(){

        Map<String, Boolean> map;
        List<String> machines = connectMapper.getAllMachineConnects();
        map = new HashMap<>();
        machines.forEach((machine)->{
            map.put(machine, false);
        });

        Iterator<String> iterator = ClientWebSocketServer.sessionMap.keySet().iterator();
        Map<String, Boolean> temp = new HashMap<>(map);
        while(iterator.hasNext()){
            temp.put(iterator.next(), true);
        }
        Set<Map.Entry<String, Boolean>> entries = temp.entrySet();
        List<MachineDTO> list = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : entries) {
            if(entry.getValue() == true){
                list.add(0,new MachineDTO(entry.getKey(), entry.getValue()));
            }else {
                list.add(new MachineDTO(entry.getKey(), entry.getValue()));
            }
        }
        String jsonMessage = new Gson().toJson(list);
        log.info("定时上传机器在线状态:{}", jsonMessage);
        BrowseWebSocketServer.sessionMap.values().forEach((session)->{
            try {
                session.getBasicRemote().sendText(jsonMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
