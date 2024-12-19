package com.zhuzheng.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: ChartDTO
 * Package: com.zhuzheng.server.dto
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/19 - 10:46
 * Version: v1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChartDTO {
    private List<String> labels;
    private List<Long> data;
}
