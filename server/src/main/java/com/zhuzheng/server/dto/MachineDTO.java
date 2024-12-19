package com.zhuzheng.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: MachineDTO
 * Package: com.zhuzheng.server.dto
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/18 - 19:44
 * Version: v1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MachineDTO {
    private String sid;
    private Boolean online;
}
