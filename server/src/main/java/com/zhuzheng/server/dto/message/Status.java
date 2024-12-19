package com.zhuzheng.server.dto.message;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Status
 * Package: com.zhuzheng.client
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/17 - 15:16
 * Version: v1.0
 */
@Data
@NoArgsConstructor
public class Status {
    private double cpuUsage;         // CPU 使用率
    private long totalMemory;        // 总内存（单位：字节）
    private long freeMemory;         // 剩余内存（单位：字节）
    private long totalDiskSpace;     // 总磁盘空间（单位：字节）
    private long freeDiskSpace;      // 剩余磁盘空间（单位：字节）
    private double memoryUsage;      // 内存利用率（百分比）
    private double diskUsage;        // 磁盘利用率（百分比）

    // 构造函数
    public Status(double cpuUsage, long totalMemory, long freeMemory, long totalDiskSpace, long freeDiskSpace) {
        this.cpuUsage = cpuUsage;
        this.totalMemory = totalMemory;
        this.freeMemory = freeMemory;
        this.totalDiskSpace = totalDiskSpace;
        this.freeDiskSpace = freeDiskSpace;
        this.memoryUsage = calculateMemoryUsage();  // 计算内存利用率
        this.diskUsage = calculateDiskUsage();     // 计算磁盘利用率
    }

    // 计算内存利用率（百分比）
    private double calculateMemoryUsage() {
        return (double) (totalMemory - freeMemory) / totalMemory * 100;
    }

    // 计算磁盘利用率（百分比）
    private double calculateDiskUsage() {
        return (double) (totalDiskSpace - freeDiskSpace) / totalDiskSpace * 100;
    }
    @Override
    public String toString() {
        // 将字节转换为 GB
        double totalMemoryGB = totalMemory / 1_073_741_824.0;
        double freeMemoryGB = freeMemory / 1_073_741_824.0;
        double totalDiskSpaceGB = totalDiskSpace / 1_073_741_824.0;
        double freeDiskSpaceGB = freeDiskSpace / 1_073_741_824.0;

        // 返回格式化的字符串
        return "Status{" +
                "cpuUsage=" + cpuUsage * 100 + "%" +
                ", totalMemory=" + String.format("%.2f", totalMemoryGB) + " GB" +
                ", freeMemory=" + String.format("%.2f", freeMemoryGB) + " GB" +
                ", totalDiskSpace=" + String.format("%.2f", totalDiskSpaceGB) + " GB" +
                ", freeDiskSpace=" + String.format("%.2f", freeDiskSpaceGB) + " GB" +
                ", memoryUsage=" + memoryUsage + "%" +
                ", diskUsage=" + diskUsage + "%" +
                '}';
    }

}
