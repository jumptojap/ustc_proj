package com.zhuzheng.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: MachineStatus
 * Package: com.zhuzheng.server.entity
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/18 - 14:09
 * Version: v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineStatus {
    private Integer id;
    private String sid;                    // 机器唯一标识符
    private LocalDateTime uploadTime;      // 上传时间
    private double cpuUsage;               // CPU 使用率
    private long totalMemory;              // 总内存（单位：字节）
    private long freeMemory;               // 剩余内存（单位：字节）
    private long totalDiskSpace;           // 总磁盘空间（单位：字节）
    private long freeDiskSpace;            // 剩余磁盘空间（单位：字节）
    private double memoryUsage;            // 内存利用率（百分比）
    private double diskUsage;              // 磁盘利用率（百分比）

}
