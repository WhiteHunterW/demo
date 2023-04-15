package com.example.demo.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/4/2
 */
@Data
public class StatisticsModel {

    @ExcelProperty(value = "工单数")
    private Integer workOrderCount;

    @ExcelProperty(value = "点位数")
    private Integer pointPositionCount;
}
