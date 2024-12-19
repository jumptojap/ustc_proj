package com.zhuzheng.client.timer;

import com.google.gson.Gson;
import com.zhuzheng.client.PhysicalClient;
import com.zhuzheng.client.constant.MessageConstant;
import com.zhuzheng.client.data.Machine;
import com.zhuzheng.client.dto.MessageDTO;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ClassName: Option
 * Package: com.zhuzheng.client
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/16 - 21:53
 * Version: v1.0
 */
public class HeartBeatTimer {

    public static Timer startHeartbeat(PhysicalClient conn, Machine machine) {
        // 使用 Timer 定时任务来向客户端发送 ping 消息
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (conn.isOpen()) {
                    System.out.println("Sending ping to server");
                    MessageDTO message = new MessageDTO(MessageConstant.HEART_BEAT_MESSAGE, null);
                    String jsonMessage = new Gson().toJson(message);
                    conn.send(jsonMessage);
                } else {
                    System.out.println("Connection closed, heartbeat finished.");
                    timer.cancel();
                }
            }
        }, 0, machine.getConfiguration().getHeartbeatInterval());  // 每隔 5 秒发送一次 ping 消息
        return timer;
    }
    public static Timer checkHeartbeat(PhysicalClient conn, Machine machine){
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(conn.heartBeatFlag.get()){
                    System.out.println("Heartbeat keeped");
                    // 保持连接就消费本次心跳标记
                    conn.heartBeatFlag.set(false);
                }else{
                    // 如果心跳标记为 false，表示未收到心跳，执行断开连接或重试等操作
                    System.out.println("Heartbeat missed, closing connection...");
                    conn.close(); // 关闭连接
                    timer.cancel();
                }
            }
        }, 0, machine.getConfiguration().getCheckHeartbeatInterval());  // 每隔 15 秒检测下是否保持连接
        return timer;
    }

}
