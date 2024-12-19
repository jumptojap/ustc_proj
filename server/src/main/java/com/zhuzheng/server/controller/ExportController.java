package com.zhuzheng.server.controller;

import com.zhuzheng.server.dto.ChartDTO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * ClassName: ExportController
 * Package: com.zhuzheng.server.controller
 * Description:
 *
 * @Author: east_moon
 * @Create: 2024/12/19 - 14:05
 * Version: v1.0
 */
@RestController
@RequestMapping("/export")
public class ExportController {
    @Autowired
    ConnectController connectController;
    @Autowired
    StatusController statusController;
    @Autowired
    CommandController commandController;
    @GetMapping
    public void exportDataToExcel(HttpServletResponse response) throws IOException {
        // 准备数据
        ChartDTO pastFiveDaysConnects = connectController.getPastFiveDaysConnects();
        ChartDTO top5Connects = connectController.getTop5Connects();
        ChartDTO averageUsage = statusController.getAverageUsage();
        ChartDTO countsOfEachCommand = commandController.getCountsOfEachCommand();
        LocalDate dateBegin = LocalDate.now().minusDays(4);
        LocalDate dateEnd = LocalDate.now();

        // 写入excel文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("templates/分布式机群监管数据报表模板.xlsx");
        XSSFWorkbook excel = new XSSFWorkbook(in);
        XSSFSheet sheet = excel.getSheet("Sheet1");

        // 填充Excel表格内容
        sheet.getRow(1).getCell(1).setCellValue("时间:"+dateBegin+"至"+dateEnd);
        int row = 3;
        for(int i = 0; i < 5; i++){
            sheet.getRow(row).getCell(1).setCellValue(pastFiveDaysConnects.getLabels().get(i));
            sheet.getRow(row).getCell(4).setCellValue(pastFiveDaysConnects.getData().get(i));
            row++;
        }
        row++;
        for(int i = 0; i < 5; i++){
            sheet.getRow(row).getCell(1).setCellValue(top5Connects.getLabels().get(i));
            sheet.getRow(row).getCell(4).setCellValue(top5Connects.getData().get(i));
            row++;
        }
        row++;
        for(int i = 0; i < 3; i++){
            sheet.getRow(row).getCell(2 * i + 1).setCellValue(averageUsage.getLabels().get(i));
            sheet.getRow(row + 1).getCell(2 * i + 1).setCellValue(averageUsage.getData().get(i));
        }
        row = row + 2;
        for(int i = 0; i < countsOfEachCommand.getLabels().size(); i++){
            int temp = i % 3;
            if(temp == 0)
                row++;
            sheet.getRow(row).getCell(2 * temp + 1).setCellValue(countsOfEachCommand.getLabels().get(i));
            sheet.getRow(row).getCell(2 * temp + 2).setCellValue(countsOfEachCommand.getData().get(i));
        }

        // 设置响应头以确保浏览器下载文件
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=distributed_machine_report.xlsx");

        // 将Excel文件写入响应输出流
        ServletOutputStream outputStream = response.getOutputStream();
        excel.write(outputStream);
        outputStream.close();
        excel.close();
    }

}
