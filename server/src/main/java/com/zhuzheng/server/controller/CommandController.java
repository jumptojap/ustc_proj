package com.zhuzheng.server.controller;

import com.zhuzheng.server.dto.ChartDTO;
import com.zhuzheng.server.dto.CommonDTO;
import com.zhuzheng.server.entity.MachineCommand;
import com.zhuzheng.server.mapper.CommandMapper;
import com.zhuzheng.server.websocket.WebSocketHandler;
import com.zhuzheng.server.websocket.ClientWebSocketServer;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: CommandController
 * Package: com.zhuzheng.server.controller
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/18 - 15:01
 * Version: v1.0
 */
@RestController
@RequestMapping("/command")
public class CommandController {
    @Autowired
    WebSocketHandler webSocketHandler;
    @Autowired
    CommandMapper commandMapper;
    @PostMapping
    public void executeCommand(@RequestParam String sid, @RequestParam String command) {
        Session session = ClientWebSocketServer.getSessionBySid(sid);
        if(session == null)
            return;
        try {
            webSocketHandler.sendControllCommandToClient(session, sid, command);
            commandMapper.insertCommand(MachineCommand.builder()
                    .sid(sid)
                    .sendCommandTime(LocalDateTime.now())
                    .command(command)
                    .build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/usage")
    public ChartDTO getCountsOfEachCommand(){
        List<CommonDTO<String, Long>> commonDTOS = commandMapper.getCountsOfEachCommand();
        List<String> labels = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        commonDTOS.forEach((commonDTO)->{
            labels.add(commonDTO.getKey());
            data.add(commonDTO.getValue());
        });
        return new ChartDTO(labels, data);
    }
}
