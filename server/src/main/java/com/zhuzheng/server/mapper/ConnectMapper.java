package com.zhuzheng.server.mapper;

import com.zhuzheng.server.dto.CommonDTO;
import com.zhuzheng.server.entity.MachineConnect;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * ClassName: ConnectMapper
 * Package: com.zhuzheng.server.mapper
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/18 - 17:39
 * Version: v1.0
 */
@Mapper
public interface ConnectMapper {
    // 查询所有连接记录对应主机名
    @Select("SELECT distinct sid FROM machine_connect")
    List<String> getAllMachineConnects();


    // 插入连接记录
    @Insert("INSERT INTO machine_connect (sid, connect_time) VALUES (#{sid}, #{connectTime})")
    void insertMachineConnect(MachineConnect machineConnect);

    @Select("SELECT DISTINCT sid FROM machine_connect WHERE sid LIKE CONCAT('%', #{sid}, '%')")
    List<String> getMachineConnect(String sid);

    @Select("SELECT DATE_FORMAT(connect_time, '%Y-%m-%d') AS connection_date, COUNT(*) AS total_connections " +
            "FROM machine_connect " +
            "WHERE connect_time >= CURDATE() - INTERVAL 5 DAY " +
            "GROUP BY DATE(connect_time) " +
            "ORDER BY DATE(connect_time) DESC")
    @Results({
            @Result(property = "key", column = "connection_date"),  // 将 connection_date 映射为 key
            @Result(property = "value", column = "total_connections")  // 将 total_connections 映射为 value
    })
    List<CommonDTO<String, Long>> getPastFiveDaysConnects();
    @Select("SELECT sid, COUNT(*) AS total_connections " +
            "FROM machine_connect " +
            "WHERE connect_time >= CURDATE() - INTERVAL 5 DAY " +
            "GROUP BY sid " +
            "ORDER BY total_connections DESC " +
            "LIMIT 5")
    @Results({
            @Result(property = "key", column = "sid"),  // 将 connection_date 映射为 key
            @Result(property = "value", column = "total_connections")  // 将 total_connections 映射为 value
    })
    List<CommonDTO<String, Long>> getTop5Connects();




}
