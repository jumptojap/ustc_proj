package com.zhuzheng.server.controller;

import com.zhuzheng.server.dto.message.Configuration;
import com.zhuzheng.server.websocket.WebSocketHandler;
import com.zhuzheng.server.websocket.ClientWebSocketServer;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * ClassName: ConfigurationController
 * Package: com.zhuzheng.server.controller
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/18 - 15:15
 * Version: v1.0
 */
@RestController
@RequestMapping("/configuration")
public class ConfigurationController {
    @Autowired
    WebSocketHandler webSocketHandler;


    @PutMapping("/{sid}")
    public void updateConfiguration(
            @PathVariable String sid,                // 使用 @PathVariable 接收路径中的 sid
            @RequestBody Configuration configuration  // 使用 @RequestBody 接收配置
    ) {
        Session session = ClientWebSocketServer.getSessionBySid(sid);
        if(session == null){
            return;
        }
        try {
            webSocketHandler.sendConfigurationToClient(session, sid, configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
