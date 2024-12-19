package com.zhuzheng.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName: Message
 * Package: com.zhuzheng.client
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/16 - 22:00
 * Version: v1.0
 */
@Data
@AllArgsConstructor
public class MessageDTO<T> {
    private Integer type;
    T data;
}
