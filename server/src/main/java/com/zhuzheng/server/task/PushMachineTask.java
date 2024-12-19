package com.zhuzheng.server.task;

import com.zhuzheng.server.websocket.WebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: OrderTask
 * Package: com.sky.task
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/11/13 - 12:30
 * Version: v1.0
 */
@Component
@Slf4j
public class PushMachineTask {
    @Autowired
    WebSocketHandler webSocketHandler;
    @Scheduled(cron = "0/1 * * * * ?")
    public void processTimeoutOrder() {

        webSocketHandler.sendMachineToBrowse();
    }

}
