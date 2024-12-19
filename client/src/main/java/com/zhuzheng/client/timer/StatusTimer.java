package com.zhuzheng.client.timer;

import com.google.gson.Gson;
import com.zhuzheng.client.PhysicalClient;
import com.zhuzheng.client.constant.MessageConstant;
import com.zhuzheng.client.data.Machine;
import com.zhuzheng.client.data.Status;
import com.sun.management.OperatingSystemMXBean;
import com.zhuzheng.client.dto.MessageDTO;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName: StateTimer
 * Package: com.zhuzheng.client.timer
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/17 - 16:26
 * Version: v1.0
 */
public class StatusTimer {
    public static Timer uploadState(PhysicalClient conn, Machine machine) {
        // 使用 Timer 定时任务来向客户端发送 ping 消息
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (conn.isOpen()) {
                    System.out.println("upload status to server");
                    Status machineStatus = getMachineStatus();
                    machine.setStatus(machineStatus);
                    MessageDTO<Status> message = new MessageDTO(MessageConstant.STATUS_UPLOAD_MESSAGE, machineStatus);
                    String jsonMessage = new Gson().toJson(message);
                    conn.send(jsonMessage);
                } else {
                    System.out.println("Connection closed, update status finished.");
                    timer.cancel();
                }
            }
        }, 0, machine.getConfiguration().getSyncFrequency());  // 每隔 5 分钟上传状态数据
        return timer;
    }
    // 获取当前机器状态的工具方法
    private static Status getMachineStatus() {
        // 创建 SystemInfo 对象来访问硬件和操作系统信息
        SystemInfo systemInfo = new SystemInfo();

        // 获取 CPU 使用率

        // 获取中央处理器（CPU）对象
        CentralProcessor processor = systemInfo.getHardware().getProcessor();


        // 获取 CPU tick 数据
        long[] ticks = processor.getSystemCpuLoadTicks();

        // 计算 CPU 使用率
        double cpuUsage = processor.getSystemCpuLoadBetweenTicks(ticks);

        // 获取内存信息
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long totalMemory = memory.getTotal();  // 总内存
        long freeMemory = memory.getAvailable();  // 剩余内存

        // 获取磁盘信息
        File file = new File("/");
        long totalDiskSpace = file.getTotalSpace();
        long freeDiskSpace = file.getFreeSpace();

        // 创建并返回机器状态对象
        return new Status(cpuUsage, totalMemory, freeMemory, totalDiskSpace, freeDiskSpace);
    }
}
