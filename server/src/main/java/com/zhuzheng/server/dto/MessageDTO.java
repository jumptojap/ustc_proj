package com.zhuzheng.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: MessageDTO
 * Package: com.zhuzheng.server.dto
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/16 - 22:57
 * Version: v1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageDTO<T> {
    private Integer type;
    private T data;
}
