package com.zhuzheng.server.constant;

/**
 * ClassName: ConfigurationConstant
 * Package: com.zhuzheng.client.constant
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/17 - 15:35
 * Version: v1.0
 */
public class ConfigurationConstant {
    // 空闲模式：机器未执行任何任务，节能状态
    public static final int MODE_IDLE = 0;

    // 正常模式：机器执行常规任务，正常工作
    public static final int MODE_NORMAL = 1;

    // 高性能模式：机器执行高负载任务，提供最大性能
    public static final int MODE_HIGH_PERFORMANCE = 2;

    // 节能模式：机器以最低功耗运行，减少资源消耗
    public static final int MODE_POWER_SAVING = 3;

    // 维护模式：机器进行自检、升级、修复等操作
    public static final int MODE_MAINTENANCE = 4;
}
