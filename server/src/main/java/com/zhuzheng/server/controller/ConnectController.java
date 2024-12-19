package com.zhuzheng.server.controller;

import com.zhuzheng.server.dto.ChartDTO;
import com.zhuzheng.server.dto.CommonDTO;
import com.zhuzheng.server.dto.MachineDTO;
import com.zhuzheng.server.mapper.ConnectMapper;
import com.zhuzheng.server.websocket.ClientWebSocketServer;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

/**
 * ClassName: ConnectController
 * Package: com.zhuzheng.server.controller
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/18 - 17:52
 * Version: v1.0
 */
@RestController
@RequestMapping("/connect")
public class ConnectController {
    @Autowired
    ConnectMapper connectMapper;

    // 查询所有连接记录对应的主机名 (sid)
    @GetMapping("/all")
    public List<String> getAllMachineConnects() {
        return connectMapper.getAllMachineConnects();
    }
    @GetMapping
    public List<MachineDTO> getMachineConnect(@RequestParam String sid) {
        // 在线的
        List<String> machines = connectMapper.getMachineConnect(sid);
        Map<String, Session> sessionMap = ClientWebSocketServer.sessionMap;
        List<MachineDTO> ls = new ArrayList<>();
        machines.forEach((machine)->{
            if(sessionMap.containsKey(machine)){
                ls.add(0,new MachineDTO(machine, true));
            }else{
                ls.add(new MachineDTO(machine, false));
            }
        });
        return ls;
    }
    @GetMapping("/past/five")
    public ChartDTO getPastFiveDaysConnects(){
        List<CommonDTO<String, Long>> commonDTOS = connectMapper.getPastFiveDaysConnects();
        Map<String, Long> map = new HashMap<>();
        for (CommonDTO<String, Long> commonDTO : commonDTOS) {
            map.put(commonDTO.getKey(), commonDTO.getValue());
        }
        // 获取今天的日期
        LocalDate today = LocalDate.now();
        List<String> labels = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        // 获取过去五天的日期
        for (int i = 4; i >= 0; i--) {
            String pastDay = today.minusDays(i).toString();  // 获取过去第i天的日期
            labels.add(pastDay);
            if(map.containsKey(pastDay))
                data.add(map.get(pastDay));
            else
                data.add(0l);
        }
        return new ChartDTO(labels, data);
    }
    @GetMapping("/top5/five")
    public ChartDTO getTop5Connects(){
        List<CommonDTO<String, Long>> commonDTOS = connectMapper.getTop5Connects();
        List<String> labels = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        commonDTOS.forEach((commonDTO)->{
            labels.add("机器" + commonDTO.getKey());
            data.add(commonDTO.getValue());
        });
        return new ChartDTO(labels, data);
    }

}
