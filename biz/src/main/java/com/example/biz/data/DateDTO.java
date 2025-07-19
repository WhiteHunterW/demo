package com.example.biz.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

/**
 * @author wenzeng
 * @date 2025/1/14
 */
@Data
public class DateDTO {

    /**
     * 日期
     */
    @ExcelProperty(value = "日期")
    private String date;

    /**
     * 人员
     */
    @ExcelProperty(value = "人员")
    private String userName;
}
