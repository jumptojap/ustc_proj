package com.zhuzheng.server.mapper;

import com.zhuzheng.server.entity.MachineStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: StatusMapper
 * Package: com.zhuzheng.server.mapper
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/18 - 14:03
 * Version: v1.0
 */
@Mapper
public interface StatusMapper {


    // 插入 MachineStatus
    @Insert("INSERT INTO machine_status (sid, upload_time, cpu_usage, total_memory, free_memory, total_disk_space, free_disk_space, memory_usage, disk_usage) "
            + "VALUES (#{sid}, #{uploadTime}, #{cpuUsage}, #{totalMemory}, #{freeMemory}, #{totalDiskSpace}, #{freeDiskSpace}, #{memoryUsage}, #{diskUsage})")
    void insertMachineStatus(MachineStatus machineStatus);

    // 查询某个特定sid的最新机器状态
    @Select("SELECT id, sid, upload_time AS uploadTime, cpu_usage AS cpuUsage, total_memory AS totalMemory, free_memory AS freeMemory, "
            + "total_disk_space AS totalDiskSpace, free_disk_space AS freeDiskSpace, memory_usage AS memoryUsage, disk_usage AS diskUsage "
            + "FROM machine_status WHERE sid = #{sid} ORDER BY upload_time DESC LIMIT 1")
    MachineStatus getLatestMachineStatusBySid(String sid); // 返回最新的机器状态

    // 查询 CPU 使用率的平均值
    @Select("SELECT AVG(cpu_usage) FROM machine_status")
    Double getAvgCpuUsage();

    // 查询 内存使用率的平均值
    @Select("SELECT AVG(memory_usage) FROM machine_status")
    Double getAvgMemoryUsage();

    // 查询 磁盘使用率的平均值
    @Select("SELECT AVG(disk_usage) FROM machine_status")
    Double getAvgDiskUsage();
}
