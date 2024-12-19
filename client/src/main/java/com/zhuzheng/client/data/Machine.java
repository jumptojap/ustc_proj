package com.zhuzheng.client.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.management.ManagementFactory;

/**
 * ClassName: Machine
 * Package: com.zhuzheng.client
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/17 - 15:15
 * Version: v1.0
 */
@Data
public class Machine {
    private String machineId;  // 机器唯一标识
    private Status status;     // 机器状态
    private Configuration configuration; // 机器配置
    public Machine(){
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        this.machineId = jvmName.substring(0, jvmName.indexOf("@"));
        this.status = new Status();
        this.configuration = new Configuration();
    }
}
