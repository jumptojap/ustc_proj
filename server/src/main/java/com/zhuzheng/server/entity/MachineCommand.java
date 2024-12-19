package com.zhuzheng.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: MachineCommand
 * Package: com.zhuzheng.server.entity
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/19 - 10:21
 * Version: v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineCommand {
    private Integer id;
    private String sid;
    private String command;
    private LocalDateTime sendCommandTime;
}
