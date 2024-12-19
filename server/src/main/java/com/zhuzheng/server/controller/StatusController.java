package com.zhuzheng.server.controller;

import com.zhuzheng.server.dto.ChartDTO;
import com.zhuzheng.server.entity.MachineStatus;
import com.zhuzheng.server.mapper.StatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: HelloController
 * Package: com.zhuzheng.server.controller
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/16 - 16:41
 * Version: v1.0
 */
@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    StatusMapper statusMapper;

    @GetMapping("/latest/{sid}")
    public MachineStatus getLatestMachineStatus(@PathVariable String sid) {
        return statusMapper.getLatestMachineStatusBySid(sid);
    }
    @GetMapping("/avg")
    public ChartDTO getAverageUsage(){
        List<Long> data = new ArrayList<>();
        Double temp = statusMapper.getAvgCpuUsage() * 100;
        data.add(temp.longValue());
        data.add(statusMapper.getAvgDiskUsage().longValue());
        data.add(statusMapper.getAvgMemoryUsage().longValue());
        List<String> labels = new ArrayList<>();
        labels.add("cpu平均利用率");
        labels.add("磁盘平均利用率");
        labels.add("内存平均利用率");
        return new ChartDTO(labels, data);
    }
}
