package com.zhuzheng.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CommonDTO
 * Package: com.zhuzheng.server.dto
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/19 - 12:10
 * Version: v1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonDTO<K, V> {
    private K key;  // 用于存储任意类型的键 (比如 String)
    private V value;  // 用于存储任意类型的值 (比如 Integer)
}