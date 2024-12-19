package com.zhuzheng.server.dto.message;

import com.zhuzheng.server.constant.ConfigurationConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName: Configuration
 * Package: com.zhuzheng.client
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/17 - 15:16
 * Version: v1.0
 */
@Data
@AllArgsConstructor
public class Configuration {
    private String ipAddress;     // IP 地址
    private long syncFrequency;    // 状态同步间隔
    private long heartbeatInterval; // 心跳信号发送间隔
    private long checkHeartbeatInterval; // 检测心跳连接间隔
    private Integer mode;          // 机器工作模式

    // 构造方法
    public Configuration() {
        this.ipAddress = "192.168.1.1";
        this.syncFrequency = 5000 * 60;
        this.heartbeatInterval = 5000L;
        this.checkHeartbeatInterval = 15000L;
        this.mode = ConfigurationConstant.MODE_NORMAL; // 默认工作模式为正常模式
    }
}
