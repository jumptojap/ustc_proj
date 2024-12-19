package com.zhuzheng.server.mapper;

import com.zhuzheng.server.dto.CommonDTO;
import com.zhuzheng.server.entity.MachineCommand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: CommandMapper
 * Package: com.zhuzheng.server.mapper
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/19 - 10:22
 * Version: v1.0
 */
@Mapper
public interface CommandMapper {
    @Insert("INSERT INTO machine_command (sid, command, send_command_time) " +
            "VALUES (#{sid}, #{command}, #{sendCommandTime})")
    void insertCommand(MachineCommand machineCommand);

    @Select("SELECT command, COUNT(*) AS usage_count\n" +
            "FROM machine_command\n" +
            "GROUP BY command\n" +
            "ORDER BY usage_count DESC;\n")
    @Results({
            @Result(property = "key", column = "command"),  // 将 connection_date 映射为 key
            @Result(property = "value", column = "usage_count")  // 将 total_connections 映射为 value
    })
    List<CommonDTO<String, Long>> getCountsOfEachCommand();
}
