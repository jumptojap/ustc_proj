package com.zhuzheng.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * ClassName: MachineConnect
 * Package: com.zhuzheng.server.entity
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/18 - 17:36
 * Version: v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MachineConnect {
    private Integer id;
    private String sid;
    private LocalDateTime connectTime;
}
